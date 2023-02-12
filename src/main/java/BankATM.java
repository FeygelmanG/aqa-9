import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankATM {

    public static boolean checkCardAndPassword(String inputCard, int inputPwd, int count) {
        String cardNum = "6228123123"; // номер карты
        int pwd = 888888; // пароль

        // проверяем аккаунт и пароль
        if (inputCard.equals(cardNum) && inputPwd == pwd) {
            return true;
        } else {
            if (count <= 2) {
                System.out.println("Sorry, пароль неверный, у вас все еще есть " + (3 - count) + " Второй шанс!");
            } else {
                System.out.println("Sorry, ваша карта заблокирована!");
            }
            return false;
        }
    }

    public static boolean auth() {
        boolean flag = true;

        Scanner input = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {
            System.out.println("Пожалуйста, вставьте свою банковскую карту:");
            try {
                String inputCard = input.next();
                System.out.println("Пожалуйста, введите ваш пароль:");
                int inputPwd = input.nextInt();

                flag = checkCardAndPassword(inputCard, inputPwd, i);
                if (flag) {
                    break;
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                System.out.println("Возникла ошибка с " + e.getClass().getCanonicalName());
                e.printStackTrace();
                flag = !flag;
                break;
            }
        }
        return flag;
    }

    public static void withdrawalOperation(Scanner input, double surplus){
        // Выполнить операцию снятия
        double getMoney = input.nextDouble();
        if (getMoney > 0) {
            if (getMoney <= surplus) {
                if (getMoney % 100 == 0) {
                    System.out.println("Пожалуйста, возьмите свою банкноту! Баланс ￥" + (surplus - getMoney));
                } else {
                    System.out.println("Sorry, я не могу принять изменения!");
                }
            } else {
                System.out.println("Sorry, баланс недостаточен!");
            }
        } else {
            System.out.println("Пожалуйста, введите правильную сумму:");
        }
    }


    public static void depositOperation (Scanner input, double surplus){
        // выполнить депозитную операцию
        double saveMoney = input.nextDouble();
        if (saveMoney > 0 && saveMoney <= 10000) {
            if (saveMoney % 100 == 0) {
                surplus += saveMoney;
                System.out.println("Депозит успешен! Баланс ￥" + surplus);
            } else {

                double backMoney = saveMoney % 100;
                surplus = saveMoney + surplus - backMoney;
                System.out.println("Депозит успешен! Баланс ￥" + surplus);
                System.out.println("Пожалуйста, заберите сдачу ￥" + backMoney);
            }
        } else if (saveMoney > 10000) {
            System.out.println("Внесите депозит до 10000 юаней за раз, пожалуйста, внесите депозит партиями!");
        } else {
            System.out.println("Депонированная банкнота является поддельной банкнотой, и она недействительна и конфискована!");
        }
    }

public static void transferOperation(Scanner input, double surplus) {
    double goMoney = input.nextDouble(); // сумма перевода
    if (goMoney > 0) {
        if (goMoney <= surplus) {
            System.out.println("Перевод успешно! Баланс is" + (surplus - goMoney));
        } else {
            System.out.println("Sorry, пожалуйста, убедитесь, что у вас достаточно средств на карте!");
        }

    } else {
        System.out.println("Перевод не выполнен! Пожалуйста, введите правильную сумму:");
    }
}

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double surplus = 1000;// баланс

        // интерфейс
        System.out.println("--------- Добро пожаловать в банкомат ICBC ---------");

        /** Выберите функцию после успешного входа в систему */
        if (auth()) {
            char answer = 'y';
            while (answer == 'y') {
                System.out.println("Пожалуйста, выберите функцию: 1. Вывод средств 2. Пополнение счета 3. Проверка баланса 4. Перевод 5. Выход");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        // Выполнить операцию снятия
                        System.out.println("---> Вывод средств");
                        System.out.println("Пожалуйста, введите сумму вывода:");
                        withdrawalOperation(input, surplus);
                        break;
                    case 2:
                        // выполнить депозитную операцию
                        System.out.println("---> Депозит");
                        System.out.println("Пожалуйста, расположите банкноты и поместите их в депозитный порт:");
                        depositOperation(input,surplus);
                        break;
                    case 3:
                        // выполнить запрос баланса
                        System.out.println("---> Проверить баланс");
                        System.out.println("Баланс на вашей карте:" + surplus);
                        break;
                    case 4:
                        // выполнить операцию переноса
                        System.out.println("---> Трансфер");
                        System.out.println("Пожалуйста, введите сумму перевода:");
                        transferOperation(input,surplus);
                        break;
                    case 5:
                        // выполнить операцию выхода
                        // System.out.println ("---> Exit");
                        System.out.println("Спасибо за ваше использование!");
                        return;
                    default:
                        System.out.println("Sorry, выбранная вами функция неверна!");
                        break;
                }// switch end
                System.out.println("Продолжить? Да / Нет");
                answer = input.next().charAt(0);

            } // while end
            System.out.println("Спасибо за ваше использование!");
        }
    }
}