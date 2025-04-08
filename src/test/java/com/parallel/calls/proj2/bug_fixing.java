public class FormValidator {
    public boolean validate(String email) {
        return email.contains("@"); // skips empty check
    }
}