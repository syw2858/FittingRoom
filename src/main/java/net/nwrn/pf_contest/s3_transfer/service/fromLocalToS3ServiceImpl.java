package net.nwrn.pf_contest.s3_transfer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.*;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class fromLocalToS3ServiceImpl implements fromLocalToS3Service {

    @Value("${properties.awsBucketName}")
    private String awsBucketName;
    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;
    private static final Logger logger = LoggerFactory.getLogger(fromLocalToS3ServiceImpl.class);

    private final S3TransferManager transferManager = S3ClientFactory.transferManager;

    public final String key = UUID.randomUUID().toString();
    public String localFileOrDirPath;

    private void setUp(String selected) {
        // 버킷 생성
        S3ClientFactory.s3Client.createBucket(b -> b.bucket(awsBucketName));

        // localFileOrDirPath 생성
        URL resource = fromLocalToS3ServiceImpl.class.getClassLoader().getResource(selected);
        localFileOrDirPath = resource.getPath();
    }

    public String uploadImageFile(String selected) {
        this.setUp(selected);
        UploadFileRequest uploadFileRequest =
                UploadFileRequest.builder()
                        .putObjectRequest(b -> b.bucket(awsBucketName).key(key))
                        .addTransferListener(LoggingTransferListener.create())
                        .source(Paths.get(localFileOrDirPath))
                        .build();

        FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

        // CompletableFuture를 사용하여 비동기 파일 업로드 작업을 수행하는 것으로 보입니다.
        CompletedFileUpload uploadResult = fileUpload.completionFuture().join();

        // ETag는 HTTP 헤더의 일종으로, 해당 리소스의 버전을 나타내는 엔터티 태그입니다. 일반적으로 파일 업로드 후에 클라이언트가 해당 파일을 요청할 때 ETag를 이용하여 캐시 유효성을 확인하는 데 사용됩니다.
        return uploadResult.response().eTag();
    }

    public Integer uploadImageDir(String selected) {
        this.setUp(selected);
        DirectoryUpload directoryUpload =
                transferManager.uploadDirectory(UploadDirectoryRequest.builder()
                        .source(Paths.get(localFileOrDirPath))
                        .bucket(awsBucketName)
                        .build());

        CompletedDirectoryUpload completedDirectoryUpload = directoryUpload.completionFuture().join();
        completedDirectoryUpload.failedTransfers().forEach(fail ->
                logger.warn("Object [{}] failed to transfer", fail.toString()));

        // CompletedDirectoryUpload 객체의 failedTransfers() 메서드를 사용하여 실패한 전송 작업에 대한 정보를 얻고, 그 수를 반환하고 있습니다.
        return completedDirectoryUpload.failedTransfers().size();
    }

    public void cleanUp(){
        S3ClientFactory.s3Client.deleteBucket(b -> b.bucket(awsBucketName));
    }

}


