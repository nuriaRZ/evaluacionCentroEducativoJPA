package gestionEntidadControlada;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JMenuBar {
	
	public Menu() {
		JMenu menuGestion = new JMenu("Gestion");
		
		menuGestion.add(crearNuevoMenuItem("Gestión Curso", new PanelGestionCurso()));
		menuGestion.add(crearNuevoMenuItem("Gestión Materia", new PanelGestionMateria()));
		menuGestion.add(crearNuevoMenuItem("Gestión Profesor", new PanelGestionProfesor()));
		menuGestion.add(crearNuevoMenuItem("Gestión Estudiante", new PanelGestionEstudiante()));
		menuGestion.add(crearNuevoMenuItem("Gestión Valoración Materia", new PanelGestionValoracionMateria()));
		menuGestion.add(crearNuevoMenuItem("Gestión Valoración Materia Mejorada", new PanelGestionValoracionMateriaMejorada()));
		this.add(menuGestion);
	}
	
	private JMenuItem crearNuevoMenuItem(String titulo, JPanel panel) {
		JMenuItem item = new JMenuItem(titulo);
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				
				// para no redimesnionar el diálogo
				dialogo.setResizable(true);
				
				// le pasamos el título del diálogo
				dialogo.setTitle(titulo);
				
				// le pasamos el JPanel que creamos para cada una de las opciones
				dialogo.setContentPane(panel);
				
				// etiquetamos el diálogo hace que todos los componentes ocupen el espacio que necesitan
				dialogo.pack();
				
				// el usuario no puede hacer clic sobre la ventana padre si el diálogo está abierto
				dialogo.setModal(true);
				
				// para centrar el diálogo en la pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
				
				// sacar el dialogo en la pantalla
				dialogo.setVisible(true);
				
			}
		});
		
		return item;
	}

}
