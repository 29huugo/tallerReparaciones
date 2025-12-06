package ControladorTaller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.interfaces.UsuarioDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.ClienteDAO;
import dao.interfaces.VehiculoDAO;

import dao.mysql.UsuarioDAOMySQL;
import dao.mysql.ReparacionDAOMySQL;
import dao.mysql.ClienteDAOMySQL;
import dao.mysql.VehiculoDAOMySQL;

import entities.Usuario;
import entities.Reparacion;
import entities.Cliente;
import entities.Vehiculo;
import utils.PasswordUtils;

public class ControladorTaller {

    private Scanner sc;
    private Usuario usuarioActual;

    private UsuarioDAO usuarioDAO;
    private ReparacionDAO reparacionDAO;
    private ClienteDAO clienteDAO;
    private VehiculoDAO vehiculoDAO;

    public ControladorTaller() throws SQLException {
        this.sc = new Scanner(System.in);
        this.usuarioActual = null;

        this.usuarioDAO = new UsuarioDAOMySQL();
        this.reparacionDAO = new ReparacionDAOMySQL();
        this.clienteDAO = new ClienteDAOMySQL();
        this.vehiculoDAO = new VehiculoDAOMySQL();
    }

    public void iniciarAplicacion() {
        int opcion = -1;
        do {
            System.out.println("\n--- TALLER MECÁNICO ---");
            System.out.println("1. Iniciar Sesión (CU2)");
            System.out.println("2. Consultar Reparaciones FINALIZADAS");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                String entrada = sc.nextLine();
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        login();
                        break;
                    case 2:
                        consultarReparacionesFinalizadas();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }

        } while (opcion != 0 && usuarioActual == null);
    }

    private void login() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Usuario usuarioDB = usuarioDAO.findByNombre(nombre);

        if (usuarioDB != null) {
            String hashedPassword = PasswordUtils.hashPassword(password);
            
            if (usuarioDB.getPassword().equals(hashedPassword)) {
                this.usuarioActual = usuarioDB;
                System.out.println("\n¡Bienvenido, " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")!");
                mostrarMenuPrincipal();
                return;
            }
        }
        System.out.println("Error de autenticación: Usuario o contraseña incorrectos.");
    }

    private void mostrarMenuPrincipal() {
        if (usuarioActual == null) return;

        String rol = usuarioActual.getRol().toLowerCase();
        int opcion;

        do {
            opcion = 0;
            System.out.println("\n--- MENÚ PRINCIPAL [" + rol.toUpperCase() + "] ---");

            try {
                switch (rol) {
                    case "mecanico":
                        opcion = mostrarMenuMecanico();
                        break;
                    case "administrador":
                        opcion = mostrarMenuAdministrador();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo con un número.");
            }

        } while (opcion != 0);

        this.usuarioActual = null;
        System.out.println("Sesión cerrada.");
        iniciarAplicacion();
    }

    private void consultarReparacionesFinalizadas() {
        System.out.println("\n--- CONSULTAR REPARACIONES FINALIZADAS ---");
        
        try {
            ArrayList<Reparacion> todasReparaciones = (ArrayList<Reparacion>) reparacionDAO.findall();
            
            List<Reparacion> reparacionesFinalizadas = todasReparaciones.stream()
                .filter(r -> r.getCoste() > 0.0)
                .collect(Collectors.toList());

            if (reparacionesFinalizadas.isEmpty()) {
                System.out.println("Actualmente no hay reparaciones finalizadas.");
                return;
            }

            System.out.printf("| %-4s | %-10s | %-40s | %-10s |%n", "ID", "FECHA", "DESCRIPCIÓN", "COSTE");
            System.out.println("-----------------------------------------------------------------");
            
            for (Reparacion r : reparacionesFinalizadas) {
                System.out.printf("| %-4d | %-10d | %-40s | %-10.2f |%n",
                    r.getId(),
                    r.getFecha_entrada(),
                    r.getDescripcion(),
                    r.getCoste());
            }
            System.out.println("-----------------------------------------------------------------");
            
        } catch (Exception e) {
            System.out.println("Error al consultar las reparaciones: " + e.getMessage());
        }
    }
    
   

    private void registrarNuevaReparacion() {
        System.out.println("\n--- REGISTRAR NUEVA REPARACIÓN (CU3) ---");
        
        Cliente cliente = obtenerOcrearCliente();
        if (cliente == null) return;
        
        Vehiculo vehiculo = obtenerOcrearVehiculo(cliente.getId());
        if (vehiculo == null) return;

        System.out.print("Descripción de la reparación: ");
        String descripcion = sc.nextLine();

        double coste = 0.0;
        try {
            System.out.print("Coste estimado: ");
            String costeStr = sc.nextLine();
            if (costeStr.isEmpty()) {
                coste = 0.0;
            } else {
                coste = Double.parseDouble(costeStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("Coste inválido. Cancelando registro.");
            return;
        }

        Reparacion nuevaReparacion = new Reparacion();
        nuevaReparacion.setDescripcion(descripcion);
        nuevaReparacion.setFecha_entrada((int) LocalDate.now().toEpochDay());
        nuevaReparacion.setCoste(coste);
        nuevaReparacion.setVehiculo_id(vehiculo.getId());
        nuevaReparacion.setUsuario_id(usuarioActual.getId());

        try {
            int idGenerado = reparacionDAO.insert(nuevaReparacion);
            if (idGenerado > 0) {
                System.out.println("Reparación registrada con éxito. ID: " + idGenerado);
            } else {
                System.out.println("Error al registrar la reparación.");
            }
        } catch (Exception e) {
            System.out.println("Error en DAO: " + e.getMessage());
        }
    }

    private Cliente obtenerOcrearCliente() {
        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine();
        
        try {
            Cliente cliente = clienteDAO.findByDni(dni);

            if (cliente == null) {
                System.out.println("Cliente no encontrado. Procediendo a registrar nuevo cliente:");
                
                Cliente nuevo = new Cliente();
                nuevo.setDni(dni);
                System.out.print("Nombre del cliente: ");
                nuevo.setNombre(sc.nextLine());
                System.out.print("Teléfono: ");
                nuevo.setTelefono(sc.nextLine());
                System.out.print("Email: ");
                nuevo.setEmail(sc.nextLine());
                
                int idGenerado = clienteDAO.insert(nuevo);
                if (idGenerado > 0) {
                    nuevo.setId(idGenerado);
                    System.out.println("Cliente registrado con ID: " + idGenerado);
                    return nuevo;
                } else {
                    System.out.println("Error al registrar nuevo cliente.");
                    return null;
                }
            }
            return cliente;

        } catch (Exception e) {
            System.out.println("Error al obtener/crear cliente: " + e.getMessage());
            return null;
        }
    }

    private Vehiculo obtenerOcrearVehiculo(int clienteId) {
        System.out.print("Matrícula del vehículo: ");
        String matricula = sc.nextLine();

        try {
            Vehiculo vehiculo = vehiculoDAO.findByMatricula(matricula);

            if (vehiculo == null) {
                System.out.println("Vehículo no encontrado. Procediendo a registrar nuevo vehículo:");
                
                Vehiculo nuevo = new Vehiculo();
                nuevo.setMatricula(matricula);
                System.out.print("Marca: ");
                nuevo.setMarca(sc.nextLine());
                System.out.print("Modelo: ");
                nuevo.setModelo(sc.nextLine());
                nuevo.setCliente_id(clienteId);
                
                int idGenerado = vehiculoDAO.insert(nuevo);
                if (idGenerado > 0) {
                    nuevo.setId(idGenerado);
                    System.out.println("Vehículo registrado con ID: " + idGenerado);
                    return nuevo;
                } else {
                    System.out.println("Error al registrar nuevo vehículo.");
                    return null;
                }
            }
            if (vehiculo.getCliente_id() != clienteId) {
                 System.out.println("El vehículo ya existe, pero está asociado a otro cliente.");
                 return null;
            }
            
            return vehiculo;

        } catch (Exception e) {
            System.out.println("Error al obtener/crear vehículo: " + e.getMessage());
            return null;
        }
    }

    private void modificarDatosReparacion(boolean soloPropias) {
        System.out.println("\n--- MODIFICAR DESCRIPCIÓN Y COSTE ---");

        try {
            List<Reparacion> reparacionesDisponibles;
            if (soloPropias) {
                reparacionesDisponibles = reparacionDAO.findByUsuario_id(usuarioActual.getId());
            } else {
                reparacionesDisponibles = reparacionDAO.findall();
            }
            
            if (reparacionesDisponibles.isEmpty()) {
                System.out.println("No hay reparaciones disponibles para modificar.");
                return;
            }

            System.out.println("Reparaciones disponibles para modificar:");
            System.out.printf("| %-4s | %-10s | %-40s | %-10s |%n", "ID", "FECHA", "DESCRIPCIÓN", "COSTE");
            System.out.println("-----------------------------------------------------------------");
            for (Reparacion r : reparacionesDisponibles) {
                System.out.printf("| %-4d | %-10d | %-40s | %-10.2f |%n",
                    r.getId(),
                    r.getFecha_entrada(),
                    r.getDescripcion(),
                    r.getCoste());
            }
            System.out.println("-----------------------------------------------------------------");
            
            System.out.print("Ingrese ID de la reparación a modificar: ");
            String idStr = sc.nextLine();
            int idReparacion = Integer.parseInt(idStr);

            Reparacion r = reparacionDAO.findById(idReparacion);
            if (r == null || (soloPropias && r.getUsuario_id() != usuarioActual.getId())) {
                System.out.println("Reparación no encontrada o no tiene permiso para modificarla.");
                return;
            }

            
            System.out.println("Descripción actual: " + r.getDescripcion());
            System.out.print("Nueva descripción (dejar vacío para mantener): ");
            String nuevaDescripcion = sc.nextLine();
            if (!nuevaDescripcion.isEmpty()) {
                r.setDescripcion(nuevaDescripcion);
            }

            System.out.println("Coste actual: " + r.getCoste());
            System.out.print("Nuevo coste (dejar vacío para mantener): ");
            String nuevoCosteStr = sc.nextLine();
            if (!nuevoCosteStr.isEmpty()) {
                try {
                    r.setCoste(Double.parseDouble(nuevoCosteStr));
                } catch (NumberFormatException e) {
                    System.out.println("Coste inválido. Se mantiene el coste actual.");
                }
            }
            
            int filas = reparacionDAO.update(r);

            if (filas > 0) {
                System.out.println("Datos de la reparación " + idReparacion + " actualizados con éxito.");
            } else {
                System.out.println("No se pudo actualizar los datos.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Entrada de ID no válida. Por favor, ingrese un número.");
        } catch (Exception e) {
             System.out.println("Error al modificar la reparación: " + e.getMessage());
        }
    }

    private void gestionClientesVehiculos() {
        System.out.println("\n--- GESTIÓN DE CLIENTES Y VEHÍCULOS ---");
        System.out.println("Lógica CU5: Implementación de CRUD para Cliente y Vehículo...");
    }

    private void gestionUsuarios() {
        System.out.println("\n--- GESTIÓN DE USUARIOS ---");
        System.out.println("Lógica CU5: Implementación de CRUD para Usuario...");
    }

    
    private void verEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DEL TALLER (CU6) ---");

        try {
            ArrayList<Reparacion> todas = (ArrayList<Reparacion>) reparacionDAO.findall();
            int totalReparaciones = todas.size();
            double costeTotal = todas.stream().mapToDouble(Reparacion::getCoste).sum();

            System.out.println("Resumen General:");
            System.out.println("   Total de reparaciones registradas: " + totalReparaciones);
            System.out.printf("   Coste estimado total acumulado: %.2f €%n", costeTotal);

        } catch (Exception e) {
             System.out.println("Error al generar estadísticas: " + e.getMessage());
        }
    }

    private int mostrarMenuMecanico() {
        System.out.println("1. Registrar nueva reparación ");
        System.out.println("2. Modificar descripción/coste de reparación PROPIA");
        System.out.println("3. Consultar TODAS mis reparaciones");
        System.out.println("0. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = 0;
        try {
            String entrada = sc.nextLine();
            opcion = Integer.parseInt(entrada);
            
            switch (opcion) {
                case 1:
                    registrarNuevaReparacion();
                    break;
                case 2:
                    modificarDatosReparacion(true);
                    break;
                case 3:
                	consultarReparacionesFinalizadas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
             System.out.println("Entrada no válida. Por favor, ingrese un número.");
        }
        return opcion;
    }

    private int mostrarMenuAdministrador() {
        System.out.println("1. Registrar nueva reparación ");
        System.out.println("2. Modificar descripción/coste de CUALQUIER reparación");
        System.out.println("3. Gestión de Clientes y Vehículos ");
        System.out.println("4. Gestión de Usuarios (CU5)");
        System.out.println("5. Ver Estadísticas (CU6)");
        System.out.println("0. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = 0;
        try {
            String entrada = sc.nextLine();
            opcion = Integer.parseInt(entrada);
            
            switch (opcion) {
                case 1:
                    registrarNuevaReparacion();
                    break;
                case 2:
                    modificarDatosReparacion(false);
                    break;
                case 3:
                    gestionClientesVehiculos();
                    break;
                case 4:
                    gestionUsuarios();
                    break;
                case 5:
                    verEstadisticas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
             System.out.println("Entrada no válida. Por favor, ingrese un número.");
        }
        return opcion;
    
    }

    public static void main(String[] args) throws SQLException {
        ControladorTaller app = new ControladorTaller();
        app.iniciarAplicacion();
    }
}
