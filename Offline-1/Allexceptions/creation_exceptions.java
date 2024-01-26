package Allexceptions;

    public class creation_exceptions extends Exception{
        public static String account_already_exists="An account already exists with this name, try to use another name";
        //public static String account_name_empty="Name cannot be empty";
        //public static String min_amount="Initial fixed deposit amount must be at least $"+ Constants.FD_MIN_AMOUNT;
        public static String account_invalid="Invalid Account Type";
        public creation_exceptions(String message){
            super(message);
        }
    }
