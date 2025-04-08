public class UserValidator {
    public boolean isValid(String name, int age) {
        if (name != null) {
            if (!name.isEmpty()) {
                if (age > 18) {
                    return true;
                }
            }
        }
        return false;
    }
}