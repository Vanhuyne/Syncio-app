package online.syncio.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.JTextComponent;

public class Validator {
    /**
    * This method checks whether a given input is not null and not blank or equal to a placeholder text.
    * If a placeholder text is provided, the input must also be different from it.
    * @param txt the text component to focus on in case of an invalid input (can be null)
    * @param input the input to check (can be null)
    * @param placeholderText the text to compare the input with (can be null)
    * @return true if the input is not null and not blank or equal to the placeholder text (if provided), false otherwise
    */
    public static boolean isNotNull(JTextComponent txt, String input, String placeholderText) {
        if(placeholderText == null) {
            if(txt != null) txt.requestFocus();
            return input != null && !input.isBlank();
        }
        else {
            if(txt != null) txt.requestFocus();
            return input != null && !input.isBlank() && !input.equalsIgnoreCase(placeholderText);
        }
    }
    
    

    /**
     * This method validates whether the input contains only letters and/or numbers, and is not blank or equal to a placeholder text.
     * If a placeholder text is provided, the input must also be different from it.
     *
     * @param txt the text component to focus on in case of an invalid input (can be null)
     * @param title the title of the input field (used in error messages)
     * @param input the input to validate (can be null)
     * @param allowNull whether the input is allowed to be null or blank
     * @param placeholderText the text to compare the input with (can be null)
     * @return an error message if the input is invalid, an empty string otherwise
     */
    public static String allowNumberText(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Please enter " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText) && !input.matches("[a-zA-Z0-9]+")) {
            if(txt != null) txt.requestFocus();
            return title + " should only allow letters and digits (a-zA-Z0-9)" + "\n";
        }
        
        return "";
    }
    
    
    
    public static String allowNumberTextUnderline(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Please enter " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText) && !input.matches("[a-zA-Z0-9_]+")) {
            if(txt != null) txt.requestFocus();
            return title + " should only allow letters, digits and underscores (a-zA-Z0-9_)" + "\n";
        }
        
        return "";
    }
    
    
    
    /**
     * This method validates whether the input contains only letters, numbers, and/or spaces, and is not blank or equal to a placeholder text.
     * If a placeholder text is provided, the input must also be different from it.
     *
     * @param txt the text component to focus on in case of an invalid input (can be null)
     * @param title the title of the input field (used in error messages)
     * @param input the input to validate (can be null)
     * @param allowNull whether the input is allowed to be null or blank
     * @param placeholderText the text to compare the input with (can be null)
     * @return an error message if the input is invalid, an empty string otherwise
     */
    public static String allowNumberTextSpace(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Vui lòng nhập " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText) && !input.matches("[a-zA-Z0-9\\s]+")) {
            if(txt != null) txt.requestFocus();
            return title + " chỉ được chứa các kí tự [a-zA-Z0-9 và kí tự khoảng trắng]" + "\n";
        }
        
        return "";
    }
    
    
    
    /**
     * This method validates whether the input contains only numbers, and is not blank or equal to a placeholder text.
     * If a placeholder text is provided, the input must also be different from it.
     *
     * @param txt the text component to focus on in case of an invalid input (can be null)
     * @param title the title of the input field (used in error messages)
     * @param input the input to validate (can be null)
     * @param allowNull whether the input is allowed to be null or blank
     * @param placeholderText the text to compare the input with (can be null)
     * @return an error message if the input is invalid, an empty string otherwise
     */
    public static String allowNumber(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Please enter " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText) && !input.matches("[0-9]+")) {
            if(txt != null) txt.requestFocus();
            return title + " should only allow digits (0-9)." + "\n";
        }
        
        return "";
    }
    
    
    
    /**
    * This method validates whether the input is a valid double value, and is not blank or equal to a placeholder text.
    * If a placeholder text is provided, the input must also be different from it.
    * @param txt the text component to focus on in case of an invalid input (can be null)
    * @param title the title of the input field (used in error messages)
    * @param input the input to validate (can be null)
    * @param allowNull whether the input is allowed to be null or blank
    * @param placeholderText the text to compare the input with (can be null)
    * @return an error message if the input is invalid, an empty string otherwise
    */
    public static String allowDouble(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(isNotNull(null, input, placeholderText) && !input.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
            if(txt != null) txt.requestFocus();
            return title + " sai định dạng" + "\n";
        }
        else if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Vui lòng nhập " + title + "\n";
        }
        
        return "";
    }
    
    
    
    /**
    * This method validates whether the input contains only Vietnamese alphabet characters and spaces.
    * 
    * @param txt The JTextComponent that the input is being validated for.
    * @param title The title of the input field, used for error messages.
    * @param input The input string to be validated.
    * @param allowNull Whether or not the input can be null.
    * @param placeholderText The placeholder text to be compared to the input string.
    * @return An error message if the input contains any characters that are not Vietnamese alphabet characters or spaces, otherwise an empty string.
    */
    public static String allowVietnameseSpace(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Vui lòng nhập " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText) && !input.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+")) {
            if(txt != null) txt.requestFocus();
            return title + " chỉ được chứa các kí tự chữ cái và kí tự khoảng trắng" + "\n";
        }
        
        return "";
    }
    
    
    
    /**
    * This method validates whether the input is a valid email address.
    * 
    * @param txt The JTextComponent that the input is being validated for.
    * @param title The title of the input field, used for error messages.
    * @param input The input string to be validated.
    * @param allowNull Whether or not the input can be null.
    * @param placeholderText The placeholder text to be compared to the input string.
    * @return An error message if the input is not a valid email address, otherwise an empty string.
    */
    public static String email(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(isNotNull(null, input, placeholderText) && !input.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            if(txt != null) txt.requestFocus();
            return title + " address format is invalid" + "\n";
        }
        else if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Please enter " + title + "\n";
        }
        
        return "";
    }
    
    
    
    /**
    * This method validates whether the input is a valid date string in the format "yyyy-mm-dd".
    * 
    * @param txt The JTextComponent that the input is being validated for.
    * @param title The title of the input field, used for error messages.
    * @param input The input string to be validated.
    * @param allowNull Whether or not the input can be null.
    * @param placeholderText The placeholder text to be compared to the input string.
    * @return An error message if the input is not a valid date string, otherwise an empty string.
    */
    public static String date(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(isNotNull(null, input, placeholderText)) {
            if(!input.matches("^\\d+\\-\\d+\\-\\d+")) {
                if(txt != null) txt.requestFocus();
                return title + " phải theo định dạng yyyy-mm-dd" + "\n";
            }
            else {
                //tao dinh dang yyyy-MM-dd
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setLenient(false);
                try {
                    df.parse(input);
                } catch (ParseException ex) {
                    if(txt != null) txt.requestFocus();
                    return title + " phải theo định dạng yyyy-mm-dd" + "\n";
                }
            }
        }
        else if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Vui lòng nhập " + title + "\n";
        }
        
        return "";
    }
    
    
    
    /**
    * This method validates whether the input is a valid double number, and is
    * within the range of 0-10 if it is not null.
    * 
    * @param txt The JTextComponent that the input is being validated for.
    * @param title The title of the input field, used for error messages.
    * @param input The input string to be validated.
    * @param allowNull Whether or not the input can be null.
    * @param placeholderText The placeholder text to be compared to the input string.
    * @return An error message if the input is not a valid double number or is not within the range of 0-10, otherwise an empty string.
    */
    public static String DoubleGrade0To10(JTextComponent txt, String title, String input, Boolean allowNull, String placeholderText) {
        if(isNotNull(null, input, placeholderText) && !input.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
            if(txt != null) txt.requestFocus();
            return title + " sai định dạng" + "\n";
        }
        else if(!isNotNull(null, input, placeholderText) && !allowNull) {
            if(txt != null) txt.requestFocus();
            return "Vui lòng nhập " + title + "\n";
        }
        else if(isNotNull(null, input, placeholderText)) {
            Double inputDouble = Double.parseDouble(input);
            if(inputDouble < 0 || inputDouble > 10) {
                if(txt != null) txt.requestFocus();
                return title + " phải từ 0-10" + "\n";
            }
        }
        
        return "";
    }

}
