public class Number {
    private int value;
    private int lenght;
    private String strValue;

    public Number(int value) {
        this.value = value;
        this.lenght = Integer.toString(value).length();
        this.strValue = Integer.toString(value);
    }

    public int getPointsNeeded(int divider){
        int mod = this.value % divider;
        if(mod == 0){
            return 0;
        }else{
            return divider - mod;
        }
    }

    public boolean isAddable(int points){
        char[] chars = this.strValue.toCharArray();
        int availblePoints = 0;
        for (char c : chars) {
            availblePoints += (9 - Integer.parseInt(String.valueOf(c)));
        }
        return points <= availblePoints;
    }

    public void add(int points, int position){
        int pointsLeft = points;
        while(position < this.lenght && pointsLeft > 0){
            int digit = this.getDigitFromPosition(position);
            while(digit < 9 && pointsLeft > 0){
                digit++;
                pointsLeft--;
            }
            this.setDigit(digit, position);
            position++;

        }
    }

    private void setDigit(int newDigit, int position){
        char[] chars = this.strValue.toCharArray();        //string value to char array
        chars[position] = Integer.toString(newDigit).charAt(0); //swap digit at index
        this.setValue(Integer.parseInt(String.valueOf(chars))); //char array back to string back to integer to set new value xD
    }

    public int getDigitFromPosition(int position){
        return Character.getNumericValue(this.strValue.charAt(position));
    }


    public int getValue() {
        return this.value;
    }

    public int getLenght(){
        return this.lenght;
    }

    public String getStringValue(){
        return this.strValue;
    }

    public void setValue(int value){
        this.value = value;
        this.lenght = Integer.toString(value).length();
        this.strValue = Integer.toString(value);
    }




}
