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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import controladores.EstudianteControlador;
import controladores.MateriaControlador;
import controladores.ProfesorControlador;
import controladores.ValoracionMateriaControlador;
import evaluacionCentroEducativoJPA.Estudiante;
import evaluacionCentroEducativoJPA.Materia;
import evaluacionCentroEducativoJPA.Profesor;
import evaluacionCentroEducativoJPA.Valoracionmateria;
import utils.CacheImagenes;





public class PanelGestionValoracionMateriaMejorada extends JPanel {

	private static final int MAX = 10;
	private static final int MIN = 0;
	private static final int DEFAULT = 5;
	
	
	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	JButton btnActualizar = new JButton("Botón actualizar alumnado");
	
	Valoracionmateria actual = null;

	
	JButton jbtGuardar = new JButton("Guardar");
	
	JSlider jslider = null;
	JLabel label = new JLabel("5");
	
	public PanelGestionValoracionMateriaMejorada () {
		this.setLayout(new BorderLayout());
		this.add(construir(), BorderLayout.CENTER);
		
		this.jslider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				label.setText(String.valueOf(jslider.getValue()));
				
			}
		});
		
	}
	
	public JPanel construir() {

		JPanel panelGestion = new JPanel();

		panelGestion.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		// Inclusión del campo "JCBprofesores"

		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesores();

		for (Profesor p : profesores) {
			jcbProfesor.addItem(p);
		}

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Profesores: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jcbProfesor.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jcbProfesor, c);

		// Inclusión del campo "JCBmateria"

		List<Materia> materias = MateriaControlador.getInstancia().findAllMaterias();

		for (Materia m : materias) {
			jcbMateria.addItem(m);
		}

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Materia: "), c);

		c.gridx = 1;
		c.gridy = 2;
		jcbProfesor.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(jcbMateria, c);

		// Inclusión del JSlider

		jslider = new JSlider(MIN, MAX, DEFAULT);
		jslider.setMinorTickSpacing(MIN);
		jslider.setMajorTickSpacing(MAX);
		jslider.setPaintTicks(true);
		jslider.setPaintLabels(true);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Nota: "), c);

		
	
		c.gridx = 1;
		c.gridy = 3;
		jcbProfesor.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(jslider, c);
		
		//Inclusión del JLabel "nota"
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Nota: "), c);
		
		
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(label, c);
	
		
		//Inclusión del JFormattedTextField para fecha
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(new JLabel("Fecha: "), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(getJFormattedTextFieldDatePersonalizado(), c);
		
		
		//Inclusión de btnActualizar
		
		c.gridx = 3;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(btnActualizar, c);
		
		//Inclusion de panel de alumnos no seleccionados
		
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(getPanelAlumnosNoSeleccionados(), c);
		
		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(getPanelBotones(), c);
		
		c.gridx = 3;
		c.gridy = 8;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(getPanelAlumnosSeleccionados(), c);

		
		return panelGestion;
	}
	
	private JFormattedTextField getJFormattedTextFieldDatePersonalizado() {
		JFormattedTextField jftf = new JFormattedTextField(new JFormattedTextField.AbstractFormatter() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					return sdf.parse(text);
				} catch (Exception e) {
					return null;
				}
			}
		});
		jftf.setColumns(10);
		jftf.setValue(new Date());
		return jftf;
	}
	
	private JPanel getPanelAlumnosNoSeleccionados() {
		JPanel panelAlumnosNoSeleccionados = new JPanel();		
		panelAlumnosNoSeleccionados.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
		
		
		
		return panelAlumnosNoSeleccionados;
		
	}
	
	private JPanel getPanelAlumnosSeleccionados() {
		JPanel panelAlumnosSeleccionados = new JPanel();		
		panelAlumnosSeleccionados.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		return panelAlumnosSeleccionados;
	}
	
	private JPanel getPanelBotones() {
		JPanel panelBotones = new JPanel();		
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		JButton btn1 = new JButton("<<");
		JButton btn2 = new JButton("<");
		JButton btn3 = new JButton(">");
		JButton btn4 = new JButton(">>");
		
		c.gridx = 1;
		c.gridy = 1;
		panelBotones.add(btn1, c);
		
		c.gridx = 1;
		c.gridy = 3;
		panelBotones.add(btn2, c);
		
		c.gridx = 1;
		c.gridy = 5;
		panelBotones.add(btn3, c);
		
		c.gridx = 1;
		c.gridy = 6;
		panelBotones.add(btn4, c);
		
		
		
		
		return panelBotones;
	}
	
	
	
}
	

	
	
	
	



	
	
	




	
	

