package gestionEntidadControlada;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controladores.EstudianteControlador;
import evaluacionCentroEducativoJPA.Estudiante;
import utils.CacheImagenes;





public class PanelGestionEstudiante extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	Estudiante actual = null;
	
	PanelDatosPersonales datos = new PanelDatosPersonales();
	
	
	/**
	 * 
	 */
	public PanelGestionEstudiante () {
		
		actual = EstudianteControlador.getInstancia().findFirst();
		this.setLayout(new BorderLayout());
		this.add(getMyJToolBar(), BorderLayout.NORTH);		
		this.add(datos, BorderLayout.CENTER);
		cargarDatosActual();
	}

	/**
	 * 
	 * @return
	 */
	private JToolBar getMyJToolBar () {
		JToolBar toolB = new JToolBar();

		
		
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST);
		
		JButton jbtAnterior = new JButton();
		asignarFuncion(jbtAnterior, "previous.png", LOAD_PREV);
		
		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", LOAD_NEXT);

		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST);
		
		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW);
		
		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE);
		
		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", REMOVE);
		
		toolB.add(jbtPrimero);
		toolB.add(jbtAnterior);
		toolB.add(jbtSiguiente);
		toolB.add(jbtUltimo);
		toolB.add(jbtNuevo);
		toolB.add(jbtGuardar);
		toolB.add(jbtEliminar);
		
		return toolB;
	}



	
	/**
	 * 
	 */
	private void nuevo () {
		this.datos.limpiarPantalla();
		this.actual = new Estudiante();
		JOptionPane.showMessageDialog(this, "Por favor, introduzca los datos del nuevo registro");
	}
	

	/**
	 * 
	 */
	private void guardar () {
		this.actual.setNombre(this.datos.getNombre());
		this.actual.setApellido1(this.datos.getApellido1());
		this.actual.setApellido2(this.datos.getApellido2());
		this.actual.setDni(this.datos.getDni());
		this.actual.setDireccion(this.datos.getDireccion());
		this.actual.setEmail(this.datos.getEmail());
		this.actual.setTelefono(this.datos.getTelefono());
		this.actual.setTipologiasexo(this.datos.getTipologiasexo());
		this.actual.setColorPreferido(this.datos.getColor());
		this.actual.setImagen(this.datos.getImagen());
		
		if (actual.getId() == 0) {
			EstudianteControlador.getInstancia().persist(actual);
		}
		else EstudianteControlador.getInstancia().merge(actual);
		
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Estudiante eliminar () {
		String respuestas[] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, 
				"¿Realmente desea eliminar el registro?", "Eliminación del registro", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
		        respuestas, respuestas[1]);

	    if(opcionElegida == 0) {
	    	Estudiante nuevoAMostrar = EstudianteControlador.getInstancia().findPrevious(actual);
	    	if (nuevoAMostrar == null) {
	    		nuevoAMostrar = EstudianteControlador.getInstancia().findNext(actual);
	    	}
	    	EstudianteControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

	    	if (nuevoAMostrar != null) {
	    		actual = nuevoAMostrar;
	    	}
	    	else {
	    		this.datos.limpiarPantalla();
	    	} 
	    }
	    return actual;
	}
	
	
	/**
	 * 
	 * @param jbt
	 * @param funcion
	 */
	private void asignarFuncion (JButton jbt, String icono, final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				
				Estudiante obtenido = null;
				if (funcion == LOAD_FIRST) 
					obtenido = EstudianteControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV) 
					obtenido = EstudianteControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT) 
					obtenido = EstudianteControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST) 
					obtenido = EstudianteControlador.getInstancia().findLast();
				if (funcion == NEW) 
					nuevo();
				if (funcion == SAVE) 
					guardar();
				if (funcion == REMOVE) 
					obtenido = eliminar();
				
				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}});
	}
	

	/**
	 * 
	 */
	private void cargarDatosActual () {
		if (this.actual != null) {
			datos.setId(String.valueOf(this.actual.getId()));
			datos.setNombre(this.actual.getNombre());
			datos.setApellido1(this.actual.getApellido1());
			datos.setApellido2(this.actual.getApellido2());
			datos.setDni(this.actual.getDni());
			datos.setDireccion(this.actual.getDireccion());
			datos.setEmail(this.actual.getEmail());
			datos.setTelefono(this.actual.getTelefono());
			datos.setTipologiaSexo(this.actual.getTipologiasexo());
			datos.setImagen(this.actual.getImagen());
			datos.setColor(this.actual.getColorPreferido());
			
		}
	}
}
