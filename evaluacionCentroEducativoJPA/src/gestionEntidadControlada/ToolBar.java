package gestionEntidadControlada;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import utils.CacheImagenes;







public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public ToolBar () {
		
		this.add(creaBoton(0,"Gestión Curso", "next.png", "Ir a Curso", new PanelGestionCurso()));
		this.add(creaBoton(1,"Gestión Materia", "next.png", "Ir a Materia", new PanelGestionMateria()));		
		this.add(creaBoton(2,"Gestión Estudiante", "next.png", "Ir a Estudiante", new PanelGestionEstudiante()));
		this.add(creaBoton(3,"Gestión Profesor", "next.png", "Ir a Profesor", new PanelGestionProfesor()));
		this.add(creaBoton(4,"Gestión Valoración Materia", "next.png", "Ir a Valoración Materia", new PanelGestionValoracionMateria()));
		this.add(creaBoton(5,"Gestión Valoración Materia Mejorada", "next.png", "Ir a Valoración Materia Mejorada", new PanelGestionValoracionMateriaMejorada()));
		
	}
	
	
	/**
	 * 
	 * @param titulo
	 * @param icono
	 * @return
	 */
	private JButton creaBoton(int id, String titulo, String icono, String toolTip, JPanel panel) {
        JButton jbt = new JButton();
        
       
        jbt.setToolTipText(toolTip);
        	jbt.addActionListener(new ActionListener() {
		
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
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
}	
	
	



