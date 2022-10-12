package ch.mhaspra.icm.contract.sender;

public record Contract(Long contractNr, Long partnerNr, String title, boolean active) {
}
