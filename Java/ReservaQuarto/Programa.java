package ReservaQuarto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ReservaExcecao.ReservaException;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Numero do quarto: ");
			int numeroQuarto = sc.nextInt();
			System.out.print("Data de entrada (dd/MM/yyyy): ");
			Date dataEntrada = sdf.parse(sc.next());
			System.out.print("Data de saída (dd/MM/yyyy): ");
			Date dataSaida = sdf.parse(sc.next());
			
			Reserva reserva = new Reserva(numeroQuarto, dataEntrada, dataSaida);
			System.out.println("Reserva: " + reserva);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			dataEntrada = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			dataSaida = sdf.parse(sc.next());
			
			reserva.updateDates(dataEntrada, dataSaida);
			System.out.println("Reserva: " + reserva);
		}
		catch (ParseException e) {
			System.out.println("Data com formato Inválido");
            main(args);
		}
		catch (ReservaException e) {
			System.out.println("Erro ao fazer reserva: " + e.getMessage());
            main(args);
		}
		catch (RuntimeException e) {
			System.out.println("Erro inesperado, favor verificar as entradas.");
            main(args);
		}

		sc.close();
	}
}