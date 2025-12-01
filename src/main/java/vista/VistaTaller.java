package vista;



	import java.util.Scanner;

	public class VistaTaller {

	    private Scanner sc;

	    public VistaTaller(Scanner sc) {
	        this.sc = sc;
	    }

	    public int mostrarMenuInicial() {
	        System.out.println("\n--- TALLER MECÁNICO ---");
	        System.out.println("1. Iniciar Sesión (CU2)");
	        System.out.println("2. Consultar TODAS las Reparaciones (Invitado)"); 
	        System.out.println("0. Salir");
	        System.out.print("Seleccione una opción: ");
	        return leerOpcion();
	    }

	    public int mostrarMenuMecanico() {
	        System.out.println("\n--- MENÚ PRINCIPAL [MECÁNICO] ---");
	        System.out.println("1. Registrar nueva reparación (CU3)");
	        System.out.println("2. Modificar descripción/coste de reparación PROPIA (CU4)"); 
	        System.out.println("3. Consultar TODAS mis reparaciones");
	        System.out.println("0. Cerrar Sesión");
	        System.out.print("Seleccione una opción: ");
	        return leerOpcion();
	    }

	    public int mostrarMenuAdministrador() {
	        System.out.println("\n--- MENÚ PRINCIPAL [ADMINISTRADOR] ---");
	        System.out.println("1. Registrar nueva reparación (CU3)");
	        System.out.println("2. Modificar descripción/coste de CUALQUIER reparación (CU4)");
	        System.out.println("3. Gestión de Clientes y Vehículos (CU5)");
	        System.out.println("4. Gestión de Usuarios (CU5)");
	        System.out.println("5. Ver Estadísticas (CU6)");
	        System.out.println("0. Cerrar Sesión");
	        System.out.print("Seleccione una opción: ");
	        return leerOpcion();
	    }
	    
	    private int leerOpcion() {
	        try {
	            return Integer.parseInt(sc.nextLine());
	        } catch (NumberFormatException e) {
	            mostrarError("Entrada no válida. Por favor, ingrese un número.");
	            return -1;
	        }
	    }
	    
	    private void mostrarError(String error) {
		       System.out.println(" error " + error);
			
		}

		public String[] pedirCredenciales() {
	        System.out.println("\n--- INICIO DE SESIÓN ---");
	        System.out.print("Nombre de usuario: ");
	        String nombre = sc.nextLine();
	        System.out.print("Contraseña: ");
	        String password = sc.nextLine();
	        return new String[]{nombre, password};
	    }
	    
	    public String pedirDni() {
	        System.out.print("DNI del cliente: ");
	        return sc.nextLine();
	    }
	    
	    public String pedirMatricula() {
	        System.out.print("Matrícula del vehículo: ");
	        return sc.nextLine();
	    }

	    public String pedirDato(String prompt) {
	        System.out.print(prompt);
	        return sc.nextLine();
	    }

	    public double pedirCosteEstimado() {
	        while (true) {
	            try {
	                System.out.print("Coste estimado: ");
	                return Double.parseDouble(sc.nextLine());
	            } catch (NumberFormatException e) {
	                mostrarError("Coste inválido. Debe ser un");
	            }
}
	    }
	}