package net.nwrn.pf_contest.exception;

public record ResException(

        Boolean invalid,
        String invalidParameters,
        String message

) {
}
