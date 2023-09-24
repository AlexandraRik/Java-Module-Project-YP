import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек разделить счёт?");
        //Получение количества гостей
        int guests = GuestAmount.getNumber(scanner);
        Dish.GetProducts(scanner);
        //Cписок продуктов
        String products = Dish.products;
        //Итоговая стоимость
        double sum = Dish.sum;
        //Деление суммы на количество гостей
        double priceForOne = sum/guests;
        //округление до целочисленного значения
        int roundNumber = WordEnding.GetInt(priceForOne);


        System.out.println("Добавленные товары:\n" + products);
        System.out.println("К оплате с каждого клиента: " + String.format("%.2f", priceForOne) + " " + WordEnding.GetEnding(roundNumber));

    }




}

class GuestAmount{

    public static int getNumber(Scanner scanner){//Метод для получения количества гостей.
        while(true){
            if(scanner.hasNextInt()){
                int guest = scanner.nextInt();
                if(guest>1)
                {
                    return guest;
                }
                else if(guest == 1){

                    System.out.println("Вы ввели одного гостя, делить счет не нужно");

                }
                else{
                    System.out.println("Введено некоректное значени");

                }

            }
            else{
                scanner.next();
                System.out.println("Вы ввели не число");
            }

        }


    }


}

class Dish{
    static double sum = 0; // Переменная для итоговой суммы счета
    static String products=""; //Переменная для списка продуктов

    Dish(String nameOfProducts, double sumOfProducts){
        sum = sumOfProducts;
        products = nameOfProducts;

    }



    public static String GetProducts(Scanner scanner){ //Метод для получения продуктов и цены

        String stopWord = "Завершить";

        while (true){
            System.out.println("Запишете блюдо");
            String product = scanner.useDelimiter("\n").next();
            System.out.println("Запишете цену");

            if(scanner.hasNextDouble()) {
                double price = scanner.nextDouble();
                if (price > 0) {
                    Dish.products = products.concat(product + "\n");
                    Dish.sum += price;
                    System.out.println("Товар успешно добавлен!\nНужно записать еще одно блюдо? Да/Завершить");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase(stopWord) == true) {
                        break;
                    }
                } else {
                    System.out.println("Число не может быть отрицательным! Попробуйте ещё!");
                }
            }
            else {
                scanner.next();
                System.out.println("Вы ввели некоректное значение, попробуйте написать цену еще раз");
            }

        }

        return products;

    }



}

class WordEnding{

    public static int GetInt(double price){ //Метод для округления в меньшую сторону
    return (int) Math.floor(price);

    }
    public static String GetEnding(int price){ //Метод для определения окончания
      double beforeLastLetter= price%100/10;
      if(beforeLastLetter== 1){
          return "рублей";
      }
      switch (price%10){
          case 1:
              return "рубль";
          case 2:
          case 3:
          case 4:
              return "рубля";
          default:
              return "рублей";
      }

    }


}
