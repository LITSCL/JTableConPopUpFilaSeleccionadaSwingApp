package cl.inacap.jtableconpopupfilaseleccionadaswingapp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class StartFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setTitle("JTableConPopUpFilaSeleccionadaSwingApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 614, 339);
		contentPane.add(table);
		
		//1. Proceso de carga de datos al JTable.
		DefaultTableModel mo = new DefaultTableModel();
		mo.addColumn("Código");
		mo.addColumn("Nombre");
		Object []fila = new Object[2];
		
		fila[0] = 1;
		fila[1] = "Daniel";	
		mo.addRow(fila);
		table.setModel(mo);
		
		fila[0] = 2;
		fila[1] = "Esteban";	
		mo.addRow(fila);
		table.setModel(mo);
		
		//2. Proceso de generar el PopUp.
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
	           
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} 
				else {
					table.clearSelection();
				}
			
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
					JPopupMenu pm = new JPopupMenu(); //Aca se crea el PopUp (La ventana).
					JMenuItem mi = new JMenuItem("Agregar", new ImageIcon("Imagenes/add.png")); //Aca se crea un JMenuItem (Para luego añadirlo al PopUp).
			   
					mi.addActionListener(f -> ejecutar(f)); //Aca se le añade el listener al JMenuItem.
			   
					pm.add(mi); //Aca se le añade el JMenuItem al PopUp.
				   
					pm.show(e.getComponent(), e.getX(), e.getY());
			   }
			}
		});	
	}
	
	private void ejecutar(ActionEvent e) { //Aca se ejecuta el código correspondiente al JMenuItem seleccionado (La idea es usar los datos de la fila seleccionada).
		JOptionPane.showMessageDialog(null, "Le diste click a 'Agregar'");
	}
}
