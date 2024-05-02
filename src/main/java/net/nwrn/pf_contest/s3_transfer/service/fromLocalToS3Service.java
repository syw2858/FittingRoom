package net.nwrn.pf_contest.s3_transfer.service;

public interface fromLocalToS3Service {

    private void setUp(String selected) {

    }

    public String uploadImageFile(String selected);

    public Integer uploadImageDir(String selected);

    public void cleanUp();

}
