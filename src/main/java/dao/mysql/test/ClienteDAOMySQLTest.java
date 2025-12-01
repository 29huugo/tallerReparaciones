package dao.mysql.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


	import dao.mysql.ClienteDAOMySQL;
	import entities.Cliente;
	import org.junit.jupiter.api.*;
	import java.sql.SQLException;
	import java.util.ArrayList;

	class ClienteDAOMySQLTest {

	    private static ClienteDAOMySQL clienteDAO;
	    private static final String DNI_PRUEBA = "99999999Z";
	    private static final String DNI_NO_EXISTE = "00000000X";

	    
	    private Cliente clienteTest;

	    @BeforeAll
	    static void setUpAll() throws SQLException {
	        clienteDAO = new ClienteDAOMySQL(); 
	    }

	    @BeforeEach
	    void setUp() {
	        clienteTest = new Cliente();
	        clienteTest.setDni(DNI_PRUEBA);
	        clienteTest.setNombre("Cliente Marcos");
	        clienteTest.setEmail("Marcos@email.com");
	        clienteTest.setTelefono("123456789");
	      
	        clienteDAO.delete(clienteTest); 
	    }

	    @AfterEach
	    void tearDown() {
	       
	        clienteDAO.delete(clienteTest);
	    }
	    
	   
	    
	    @Test
	    void testInsertSuccess() {
	        int filasAfectadas = clienteDAO.insert(clienteTest);
	        
	        
	        assertEquals(1, filasAfectadas, "El insert debe devolver 1 fila afectada.");
	        
	        
	        Cliente clienteInsertado = clienteDAO.findByDni(DNI_PRUEBA);
	        assertNotNull(clienteInsertado, "El cliente insertado debe ser encontrado en la DB.");
	        assertEquals(clienteTest.getNombre(), clienteInsertado.getNombre());
	    }
	    
	   

	    @Test
	    void testFindByDniExists() {
	      
	        clienteDAO.insert(clienteTest); 
	        
	        Cliente clienteEncontrado = clienteDAO.findByDni(DNI_PRUEBA);
	        
	      
	        assertNotNull(clienteEncontrado, "Debe encontrar el cliente por DNI.");
	        assertEquals(DNI_PRUEBA, clienteEncontrado.getDni());
	    }

	    @Test
	    void testFindByDniNotExists() {
	        Cliente clienteNoEncontrado = clienteDAO.findByDni(DNI_NO_EXISTE);
	        
	       
	        assertNull(clienteNoEncontrado, "No debe encontrar un cliente con un DNI inexistente.");
	    }
	    
	    @Test
	    void testFindAll() {
	       
	        clienteDAO.insert(clienteTest);
	        
	        ArrayList<Cliente> listaClientes = clienteDAO.findall();
	        
	       
	        assertFalse(listaClientes.isEmpty(), "La lista de clientes no debe estar vacía.");
	        
	       
	        boolean encontrado = listaClientes.stream()
	            .anyMatch(c -> c.getDni().equals(DNI_PRUEBA));
	            
	        assertTrue(encontrado, "El cliente insertado debe aparecer en la lista findall.");
	    }

	   

	    @Test
	    void testUpdateSuccess() {
	        
	        clienteDAO.insert(clienteTest); 
	        Cliente clienteGuardado = clienteDAO.findByDni(DNI_PRUEBA);
	        
	       
	        String nuevoEmail = "new.email@updated.es";
	        String nuevoTelefono = "987654321";
	        
	        clienteGuardado.setEmail(nuevoEmail);
	        clienteGuardado.setTelefono(nuevoTelefono);
	        
	        
	        int filasAfectadas = clienteDAO.update(clienteGuardado);
	        
	       
	        assertEquals(1, filasAfectadas, "La actualización debe afectar exactamente a una fila.");
	        
	        
	        Cliente clienteActualizado = clienteDAO.findByDni(DNI_PRUEBA);
	        assertEquals(nuevoEmail, clienteActualizado.getEmail(), "El email debe haberse actualizado.");
	        assertEquals(nuevoTelefono, clienteActualizado.getTelefono(), "El teléfono debe haberse actualizado.");
	        assertEquals(clienteTest.getNombre(), clienteActualizado.getNombre(), "El nombre no debe haber cambiado.");
	    }
	    
	   

	    @Test
	    void testDeleteSuccess() {
	       
	        clienteDAO.insert(clienteTest); 

	        
	        int filasAfectadas = clienteDAO.delete(clienteTest);
	        
	       
	        assertEquals(1, filasAfectadas, "La eliminación debe afectar exactamente a una fila.");
	        
	       
	        Cliente clienteEliminado = clienteDAO.findByDni(DNI_PRUEBA);
	        assertNull(clienteEliminado, "El cliente debe ser NULL después de la eliminación.");
	    }
	    
	    @Test
	    void testDeleteNotExists() {
	        
	        Cliente clienteInexistente = new Cliente();
	        clienteInexistente.setDni(DNI_NO_EXISTE);
	        
	        int filasAfectadas = clienteDAO.delete(clienteInexistente);
	        
	       
	        assertEquals(0, filasAfectadas, "La eliminación de un DNI inexistente debe afectar 0 filas.");
	    }

	}


