package gestionEntidadControlada;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import controladores.TipologiaSexoControlador;
import evaluacionCentroEducativoJPA.Tipologiasexo;
import utils.CacheImagenes;



public class PanelDatosPersonales extends JPanel {
	
	 JTextField jtfId = new JTextField(5);
	 JTextField jtfNombre = new JTextField(25);
	 JTextField jtfApellido1 = new JTextField(25);
	 JTextField jtfApellido2 = new JTextField(25);
	 JTextField jtfDni = new JTextField(25);
	 JTextField jtfDireccion = new JTextField(25);
	 JTextField jtfEmail = new JTextField(25);
	 JTextField jtfTelefono = new JTextField(25);
	 JComboBox<Tipologiasexo> jcbSexo = new JComboBox<Tipologiasexo>();
	 JTextField jtfColor = new JTextField(25);
	 JButton jbtCambiarColor = new JButton("Cambiar Color");
	 JScrollPane spImagen = new JScrollPane();
	 JButton jbtImagen = new JButton("Cambiar Imagen");
	 byte[] imagen;
	 JFileChooser jfc = new JFileChooser();
	 
	 
	 
	
	public PanelDatosPersonales() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		
	    // Inclusiï¿½n del campo "Identificador"
		//c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.anchor = GridBagConstraints.EAST;
	    c.insets = new Insets(2, 2, 2, 2);
	    this.add(new JLabel("Identificador: "), c);
	    
		c.gridx = 1;
	    c.gridy = 1;
	    jtfId.setEnabled(false);
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);
		
		// Inclusiï¿½n del campo "Nombre"
		c.gridx = 0;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Nombre: "), c);
	    
		c.gridx = 1;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, c);
		
		// Inclusiï¿½n del campo "Apellido1"
		c.gridx = 0;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Primer Apellido: "), c);
	    
		c.gridx = 1;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido1, c);
		
		// Inclusiï¿½n del campo "Apellido2"
		c.gridx = 0;
	    c.gridy = 4;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Segundo Apellido: "), c);
	    
		c.gridx = 1;
	    c.gridy = 4;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido2, c);
		
		// Inclusiï¿½n del campo "DNI"
		c.gridx = 0;
	    c.gridy = 5;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("DNI: "), c);
	    
		c.gridx = 1;
	    c.gridy = 5;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfDni, c);
		
		// Inclusiï¿½n del campo "Email"
		c.gridx = 0;
	    c.gridy = 6;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Email: "), c);
	    
		c.gridx = 1;
	    c.gridy = 6;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfEmail, c);
		
		// Inclusiï¿½n del campo "Telefono"
		c.gridx = 0;
	    c.gridy = 7;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Telefono: "), c);
	    
		c.gridx = 1;
	    c.gridy = 7;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfTelefono, c);
		
		// Inclusiï¿½n del campo "Sexo"
		
		List<Tipologiasexo> tsexos = TipologiaSexoControlador.getInstancia().findAllTipologiasSexo();
		for (Tipologiasexo s : tsexos) {
			jcbSexo.addItem(s);
		}
		
		c.gridx = 0;
	    c.gridy = 8;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Sexo: "), c);
	    
		c.gridx = 1;
	    c.gridy = 8;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jcbSexo, c);
		
		// Inclusión del campo "color"
		
		c.gridx = 0;
	    c.gridy = 9;
	    c.gridwidth = 1;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Color: "), c);
	    
		c.gridx = 1;
	    c.gridy = 9;
	    c.anchor = GridBagConstraints.WEST;
	    jtfColor.setEnabled(true);
		this.add(jtfColor, c);
		
		// Inclusión botón "cambiarColor"
		
		c.gridx = 2;
	    c.gridy = 9;
	    c.anchor = GridBagConstraints.WEST;
	    jtfColor.setEnabled(true);
		this.add(jbtCambiarColor, c);
		
		jbtCambiarColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarColor();
				
			}
		});
		
		//Inclusion de JScrollPane
		
				c.gridheight = 4;
				c.gridx = 2;
				c.gridy = 1;
				spImagen.setPreferredSize(new Dimension(100, 100));
				add(spImagen, c);
				
				//Inclusión de Boton Imagen
				c.gridheight = 1;
				c.gridx = 2;
			    c.gridy = 5;
			    c.anchor = GridBagConstraints.WEST;
			    jtfColor.setEnabled(true);
				this.add(jbtImagen, c);
				
				jbtImagen.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						seleccionarImagen(getImagen());
						
					}
				});
		
		
		
		
	
		

	}
	
	public void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfNombre.setText("");
		this.jtfApellido1.setText("");
		this.jtfApellido2.setText("");
		this.jtfDni.setText("");
		this.jtfDireccion.setText("");
		this.jtfEmail.setText("");
		this.jtfTelefono.setText("");
		this.jcbSexo.setSelectedIndex(0);
		this.jtfColor.setText("");
		this.setBackground(Color.lightGray);
		this.spImagen.setViewportView(null);
		
	}
	
	public void seleccionarColor() {
		Color color = (new JColorChooser().showDialog(null, "Elige un color", Color.gray));
		// cuando se elige un color, el color inicial deja de ser null
		if (color != null) {
			String strColor = "#"+Integer.toHexString(color.getRGB()).substring(2);
			this.jtfColor.setText(strColor);
			this.setColor(strColor);
			this.setBackground(color);
	
		}
	}
	
	public byte[] seleccionarImagen(byte[] imagenActual) {
		this.jfc = new JFileChooser();
		byte[] imagenSeleccionada = null;
		this.jfc.setCurrentDirectory(new File("C:\\"));
		this.jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.jfc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Archivos de imagen *.jpg , .png , .jpeg o .gif";
			}

			@Override
			public boolean accept(File f) {
				if (f.isFile() && f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".png")
						|| f.getAbsolutePath().endsWith(".jpeg") || f.getAbsolutePath().endsWith(".gif"));
					
				return true;

			}
		});
		
		int seleccionUsuario = jfc.showOpenDialog(null);

		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = this.jfc.getSelectedFile();

			

			if (fichero.isFile()) {
				try {
					imagenSeleccionada = Files.readAllBytes(fichero.toPath());
					ImageIcon imagenProvisional = new ImageIcon(imagenSeleccionada);
					if (imagenProvisional.getIconWidth() > 800 || imagenProvisional.getIconHeight() > 800) {
						JOptionPane.showMessageDialog(null, "La imagen es demasiado grande");
						return imagenActual;
					}
					setImagen(imagenSeleccionada);
					return imagenSeleccionada;
					

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		imagenSeleccionada = imagenActual;
		
		return imagenActual;

		
		
	}

	/**
	 * @return the jtfId
	 */
	public String getId() {
		return jtfId.getText();
	}

	/**
	 * @param jtfId the jtfId to set
	 */
	public void setId(String id) {
		this.jtfId.setText(id);
	}

	/**
	 * @return the jtfNombre
	 */
	public String getNombre() {
		return jtfNombre.getText();
	}

	/**
	 * @param jtfNombre the jtfNombre to set
	 */
	public void setNombre(String nombre) {
		this.jtfNombre.setText(nombre);
	}

	/**
	 * @return the jtfApellido1
	 */
	public String getApellido1() {
		return jtfApellido1.getText();
	}

	/**
	 * @param jtfApellido1 the jtfApellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.jtfApellido1.setText(apellido1);
	}

	/**
	 * @return the jtfApellido2
	 */
	public String getApellido2() {
		return jtfApellido2.getText();
	}

	/**
	 * @param jtfApellido2 the jtfApellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.jtfApellido2.setText(apellido2);
	}

	/**
	 * @return the jtfDni
	 */
	public String getDni() {
		return jtfDni.getText();
	}

	/**
	 * @param jtfDni the jtfDni to set
	 */
	public void setDni(String dni) {
		this.jtfDni.setText(dni);
	}

	/**
	 * @return the jtfDireccion
	 */
	public String getDireccion() {
		return jtfDireccion.getText();
	}

	/**
	 * @param jtfDireccion the jtfDireccion to set
	 */
	public void setDireccion(String direccion) {
		this.jtfDireccion.setText(direccion);
	}

	/**
	 * @return the jtfEmail
	 */
	public String getEmail() {
		return jtfEmail.getText();
	}

	/**
	 * @param jtfEmail the jtfEmail to set
	 */
	public void setEmail(String email) {
		this.jtfEmail.setText(email);
	}

	/**
	 * @return the jtfTelefono
	 */
	public String getTelefono() {
		return jtfTelefono.getText();
	}

	/**
	 * @param jtfTelefono the jtfTelefono to set
	 */
	public void setTelefono(String telefono) {
		this.jtfTelefono.setText(telefono);
	}

	/**
	 * @return the jcbSexo
	 */
	public Tipologiasexo getTipologiasexo() {
		return (Tipologiasexo) this.jcbSexo.getSelectedItem();
	}

	/**
	 * @param jcbSexo the jcbSexo to set
	 */
	public void setTipologiaSexo(Tipologiasexo sexo) {
		if (sexo == null && this.jcbSexo.getItemCount() > 0) {
			this.jcbSexo.setSelectedIndex(0);
		}
		else {
			for (int i = 0; i < this.jcbSexo.getItemCount(); i++) {
				Tipologiasexo sexoEnJCombo = this.jcbSexo.getItemAt(i);
				if (sexo.getId() == sexoEnJCombo.getId()) {
					this.jcbSexo.setSelectedIndex(i);
				}
			}
		}
	}
	
	public String getColor() {
		return jtfColor.getText();
	}
	
	public void setColor(String color) {
		this.jtfColor.setText(color);
		
		try {
			this.setBackground(Color.decode(color));
		}catch (Exception e) {
			this.setBackground(Color.GRAY);
		}
	}
	
	protected JScrollPane scrollPane(String imagen) {

		JLabel jlb = new JLabel(CacheImagenes.getCacheImagenes().getIcono(imagen));

		JScrollPane jsp = new JScrollPane(jlb);
		return jsp;

	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
		
		if (imagen != null && imagen.length > 0) {
			ImageIcon icono = new ImageIcon(this.imagen);
			JLabel jblIcono = new JLabel(icono);
			jblIcono.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					this.mostrarMenu(e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					this.mostrarMenu(e);
				}
				
				private void mostrarMenu(MouseEvent e) {
					if (e.isPopupTrigger()) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(new JMenuItem(icono.getIconWidth() + "x" + icono.getIconHeight() + "pixeles"));
						menu.addSeparator();
						JMenuItem miImagen = new JMenuItem("Seleccionar una imagen");
						miImagen.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								jfc = new JFileChooser();
								
							}
						});
						menu.add(miImagen);
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
				
			});
			this.spImagen.setViewportView(jblIcono);
		} else {
			JLabel jblIcono = new JLabel("No hay imagen seleccionada");
			this.spImagen.setViewportView(jblIcono);
		}
	}
	
	
	
	
	
	

}
