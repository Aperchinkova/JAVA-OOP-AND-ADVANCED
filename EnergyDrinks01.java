import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnergyDrinks01 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] first=scanner.nextLine().split("[, ]+");
        String[] second=scanner.nextLine().split("[, ]+");
        ArrayDeque<Integer> caffeineStack=new ArrayDeque<>();
        ArrayDeque<Integer> energyDrinkQueue=new ArrayDeque<>();
        for(int i=0;i<first.length;i++){
            caffeineStack.push(Integer.parseInt(first[i]));
        }
        for(int j=0;j< second.length;j++){
            energyDrinkQueue.offer(Integer.parseInt(second[j]));
        }
        int maxCaffeine=300;
        int initialCaffeine=0;

        while (!energyDrinkQueue.isEmpty() && !caffeineStack.isEmpty()){
            int lastCaffeine=caffeineStack.peek();
            int firstDrink=energyDrinkQueue.peek();
            // To calculate the caffeine in the drink take the last milligrams of caffeinе and the first energy drink, and multiply them.
            int multiplication=lastCaffeine*firstDrink;
            if(multiplication<=maxCaffeine){
                initialCaffeine+=multiplication;
                caffeineStack.pop();
                energyDrinkQueue.poll();
                maxCaffeine-=multiplication;
            }else if(multiplication>maxCaffeine){
                caffeineStack.pop();
                energyDrinkQueue.poll();
                energyDrinkQueue.addLast(firstDrink);
                if(initialCaffeine>0){
                    initialCaffeine-=30;
                    maxCaffeine+=30;
                }

            }
        }
        if(!energyDrinkQueue.isEmpty()){
            String remaining=energyDrinkQueue.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Drinks left: "+remaining);
        }else{
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.",initialCaffeine);


    }
}
