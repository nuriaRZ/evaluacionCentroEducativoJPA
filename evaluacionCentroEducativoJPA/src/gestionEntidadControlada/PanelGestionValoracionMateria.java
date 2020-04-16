package gestionEntidadControlada;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controladores.EstudianteControlador;
import controladores.MateriaControlador;
import controladores.ProfesorControlador;
import controladores.ValoracionMateriaControlador;
import evaluacionCentroEducativoJPA.Estudiante;
import evaluacionCentroEducativoJPA.Materia;
import evaluacionCentroEducativoJPA.Profesor;
import evaluacionCentroEducativoJPA.Valoracionmateria;
import utils.CacheImagenes;





public class PanelGestionValoracionMateria extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	
	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	JButton jbtRefrescar = new JButton("Botón refrescar alumnado");
	JScrollPane jspAlumno = new JScrollPane();
	Valoracionmateria actual = null;
	Materia materia = null;
	Profesor profesor = null;
	List<EstudianteJSpinner> spinners = new ArrayList<EstudianteJSpinner>();
	JButton jbtGuardar = new JButton("Guardar");
	
	
	/**
	 * 
	 */
	public PanelGestionValoracionMateria () {
		this.setLayout(new BorderLayout());
		this.add(getMyJPanel(), BorderLayout.CENTER);
		
	}
	
	private JPanel getMyJPanel() {
		JPanel panel = new JPanel();
		setBackground(Color.decode("#b2cbd1"));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		List<Materia> materias = MateriaControlador.getInstancia().findAllMaterias();
		for(Materia m : materias) {
			jcbMateria.addItem(m);
		}
		
	  // Inclusiï¿½n del campo "jcbMateria"
		
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.anchor = GridBagConstraints.EAST;
	    panel.add(new JLabel("Materia: "), c);
	    
		c.gridx = 1;
	    c.gridy = 1;	   
	    c.anchor = GridBagConstraints.WEST;
		panel.add(jcbMateria, c);
		
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesores();
		for(Profesor p : profesores) {
			jcbProfesor.addItem(p);
		}
		
		// Inclusiï¿½n del campo "jcbProfesor"
		c.gridx = 0;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.EAST;
	    panel.add(new JLabel("Profesor: "), c);
	    
		c.gridx = 1;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.WEST;
		panel.add(jcbProfesor, c);
		jcbProfesor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jcbMateria.getSelectedItem();
				
			}
		});
		
		
		
		
		
		c.gridx = 3;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtRefrescar, c);
	    jbtRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jspAlumno.setViewportView(getPanelAlumnos());
			}
		});
	    
	    c.gridx = 1;
		c.gridy = 4;
		c.weighty = 1;
		c.anchor = new GridBagConstraints().CENTER;
		jspAlumno.setPreferredSize(new Dimension (250, 250));
		panel.add(jspAlumno, c);
	    
	    c.gridx = 1;
	    c.gridy = 15;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtGuardar, c);
	    jbtGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (spinners != null) {
					Profesor p = (Profesor) jcbProfesor.getSelectedItem();
					Materia m = (Materia) jcbMateria.getSelectedItem();
					for(EstudianteJSpinner spinner : spinners) {
						Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(p, m, spinner.estudiante);
					if (valoracion != null) {
						valoracion.setValoracion(((Integer) spinner.getValue()).floatValue());
						ValoracionMateriaControlador.getInstancia().merge(valoracion);
					}
					else {
						Valoracionmateria v = new Valoracionmateria();
						v.setEstudiante(spinner.estudiante);
						v.setMateria(m);
						v.setProfesor(p);
						ValoracionMateriaControlador.getInstancia().persist(v);
					}
					}
				}
				
			}
		});
		

		
		
		return panel;
	}
	
	
	
	private JPanel getPanelAlumnos() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
		this.spinners.clear();
		int i = 2;
		for (Estudiante e : estudiantes) {
			c.fill = 0;
			c.gridx = 0;
			c.gridy = 0 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets(2, 2, 2, 2);
			panel.add(new JLabel(e.toString()), c);
			c.gridx = 1;
			c.gridy = 0 + i;
			c.anchor = GridBagConstraints.WEST;
			EstudianteJSpinner spinner = new EstudianteJSpinner(e);
			Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(
					(Profesor)this.jcbProfesor.getSelectedItem(), (Materia)this.jcbMateria.getSelectedItem(), e);
			this.spinners.add(spinner);
			
			if(valoracion != null) {
				spinner.setValue(new Integer((int) valoracion.getValoracion()));
			}
			panel.add(spinner, c);
			i++;
		}
		
		return panel;
	}
	
	private class EstudianteJSpinner extends JSpinner {
		Estudiante estudiante = null;
		
		public EstudianteJSpinner (Estudiante estudiante) {
			super();
			this.estudiante = estudiante;	
			
		}
	}
	

	
	
	
	



	
	
	




	
	
}
