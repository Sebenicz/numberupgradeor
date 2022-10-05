import java.util.ArrayList;
import java.util.Scanner;


public class Main{
     public static void main(String[] args) {
        int numberOfNumbers = 3;
        int points = 6;
        int divider = 3;
        ArrayList<Number> numbers = getinput(numberOfNumbers);
        printNumbers(numbers);
        ArrayList<Number> changedNums = changeNumbers(points, divider, numbers); 
        printNumbers(changedNums);
    }


    private static ArrayList<Number> getinput(int numberOfNumbers){
        Scanner myObj = new Scanner(System.in);  // i know its bad but i ran out of time
        ArrayList<Number> numbers = new ArrayList<Number>(); 
        for(int i=0; i<numberOfNumbers; i++){
            System.out.println("Enter number: ");
            String numberVal = myObj.nextLine();
            numbers.add(new Number(Integer.parseInt(numberVal)));
        }
        return numbers;
    }

    private static ArrayList<Number> changeNumbers(int points, int divider, ArrayList<Number> numbers){
        int sparePoints = points;
        
        for (Number number : numbers) {
            int pointsNeededForDivisibility = number.getPointsNeeded(divider);
            number.add(pointsNeededForDivisibility, 0);
            sparePoints -= pointsNeededForDivisibility;
        }
        if(sparePoints < divider){
            return numbers;
        }else{
            int highIndex = 0;
            int timesToAdd = sparePoints/divider;
            ArrayList<Number> addableNumbers = new ArrayList<Number>();
            ArrayList<Number> nonAddable = new ArrayList<Number>();
            //make a list of addable and not addable numbers
            for (Number number : numbers) {
                if(number.isAddable(divider)){addableNumbers.add(number);}
                else{nonAddable.add(number);}
            } 
            //who knows whats going on here, i hope it works jk i know but no time to explain here
            //future me here, it works as planned 
            for (int i = 0; i < timesToAdd; i++) {
                Number highestNumber = new Number(0);
                Number currentNumber = new Number(0);
                for (Number number : addableNumbers) {
                    currentNumber.setValue(number.getValue());
                    currentNumber.add(divider, 0);
                    if (currentNumber.getValue() > highestNumber.getValue()) {
                        highestNumber.setValue(currentNumber.getValue());
                        highIndex = addableNumbers.indexOf(number);
                    }    
                }
                addableNumbers.get(highIndex).add(divider, 0);
                if(!addableNumbers.get(highIndex).isAddable(divider)){
                    nonAddable.add(addableNumbers.get(highIndex));
                    addableNumbers.remove(highIndex);
                }
                
            }
            for (Number number : addableNumbers) {
                nonAddable.add(number);            
            }
            return nonAddable;

        }
    }

    private static void printNumbers(ArrayList<Number> numbers){
        System.out.println("numbers: ");
        for (Number number : numbers) {
            System.out.println(number.getStringValue());
        }
    }
}