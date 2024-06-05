package uz.com.tuir.loanagreementmanager.constant;


import lombok.Getter;

@Getter
public enum ClaimsType {
    USERNAME("username"),
    JTI("jti");
    private final String value;

    ClaimsType(String value) {
        this.value = value;
    }
}
