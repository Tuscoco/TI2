package Main;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);//Cria um novo Scanner
		
		System.out.println("Digite um numero:");
		int n1 = scan.nextInt();//Recebe um numero inteiro e o armazena na variável n1
		
		System.out.println("Digite outro numero:");
		int n2 = scan.nextInt();//Recebe um numero inteiro e o armazena na variável n2
		
		int s = n1 + n2;//Realiza a soma e a armazena na variável s
		
		System.out.println("Soma: " + s);//Mostra na tela a soma efetuada
		
		scan.close();//Fecha o Scanner

	}

}
