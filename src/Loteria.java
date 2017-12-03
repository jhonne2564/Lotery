import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import archivo.Archivo;

public class Loteria implements ActionListener {

	/**
	 * @param args
	 */
	int numeros[];
	JButton btnSaveFile, botonPasar, botonEliminar;
	JTextField campo;
	JList list1;
	JList list;
	String archivo = "Loteria.dat";

	public void loadInterface() {

		JFrame v12 = new JFrame("Lottery");
		v12.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(this);
		btnSaveFile.setBounds(200, 10, 100, 20);
		v12.getContentPane().add(btnSaveFile);

		botonPasar = new JButton("<<<");
		botonPasar.addActionListener(this);
		botonPasar.setBounds(110, 60, 80, 20);
		v12.getContentPane().add(botonPasar);

		campo = new JTextField();
		campo.setBounds(310, 10, 100, 20);
		v12.getContentPane().add(campo);

		list1 = new JList();
		JScrollPane scroll2 = new JScrollPane(list1);
		scroll2.setBounds(200, 40, 100, 300);

		DefaultTableModel tablemodel, tablemodel2;
		list = new JList();
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(10, 10, 100, 300);

		numeros = null;
		try {
			numeros = (int[]) Archivo.cargar(archivo);
		} catch (Exception e) {
			System.out.println("Error cargando el archivo " + archivo);
			numeros = new int[1000];

		}
		if (numeros == null) {
			numeros = new int[1000];
		}

		// while (true) {
		// String entrada = JOptionPane.showInputDialog("Numero");
		// if (entrada.equals(""))
		// break;
		// numeros[Integer.parseInt(entrada)]++;
		// }
		v12.getContentPane().add(scroll);
		v12.getContentPane().add(scroll2);
		v12.setSize(500, 500);
		v12.setLayout(null);
		// v12.pack();
		v12.setLocationRelativeTo(null);
		v12.setVisible(true);
		v12.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		// Loteria l=new Loteria();
		// l.loadInterface();
		new Loteria().processFromTo("4487-4519");

		// for (int i = 4487; i < 4519; i++) {
		// new Loteria().saveFile(String.valueOf(i));
		// }

		// new Loteria().saveFile("4519");
	}

	List<String> numeroslista = null;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == btnSaveFile) {
			String sCampo = campo.getText();
			if (sCampo.contains("-")) {

			} else {
				saveFile(sCampo.trim());
			}
		}

		else {

		}

	}

	private InputStream inputS;
	private FileOutputStream fos;
	private DataInputStream entrada;

	private void processFromTo(String sorteo) {

		int posicion1[] = new int[10];
		int posicion2[] = new int[10];
		int posicion3[] = new int[10];
		int posicion4[] = new int[10];

		int posicionserie1[] = new int[10];
		int posicionserie2[] = new int[10];
		int posicionserie3[] = new int[10];
		List<String> numeros = new ArrayList<>();
		List<String> series = new ArrayList<>();
		String serieFromTo[] = sorteo.split("-");
		String serie = null;
		for (int i = Integer.parseInt(serieFromTo[0].trim()); i <= Integer
				.parseInt(serieFromTo[1].trim()); i++) {
			FileInputStream fstream;
			try {

				fstream = new FileInputStream("pages/" + i + "file.html");

				// Creamos el objeto de entrada
				entrada = new DataInputStream(fstream);
				// Creamos el Buffer de Lectura
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(entrada));
				String strLinea;
				// Leer el archivo linea por linea
				while ((strLinea = buffer.readLine()) != null) {
					// Imprimimos la l�nea por pantalla
					if (strLinea.contains("class=\"Res_ResultSeco\"")) {
						String premioMayor = strLinea.trim();
						strLinea = buffer.readLine();
						if (strLinea.contains("class=\"Res_ResultSeco\"")) {
							premioMayor = premioMayor.substring(64, 68);
							premioMayor = premioMayor.trim();

							strLinea = strLinea.trim();
							serie = strLinea.substring(65, 68);
							serie = serie.trim();
							numeros.add(serie);
							int p1 = Integer.parseInt(""
									+ premioMayor.charAt(0));
							int p2 = Integer.parseInt(""
									+ premioMayor.charAt(1));
							int p3 = Integer.parseInt(""
									+ premioMayor.charAt(2));
							int p4 = Integer.parseInt(""
									+ premioMayor.charAt(3));

							int ps1 = Integer.parseInt("" + serie.charAt(0));
							int ps2 = Integer.parseInt("" + serie.charAt(1));
							int ps3 = Integer.parseInt("" + serie.charAt(2));

							
							posicion1[p1]++;
							posicion2[p2]++;
							posicion3[p3]++;
							posicion4[p4]++;

							posicionserie1[ps1]++;
							posicionserie2[ps2]++;
							posicionserie3[ps3]++;
							// System.out.println(premioMayor);
							// System.out.println(serie);

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Primera posición");
		String sp1 = "1era posición", sp2 = "2da posicion", sp3 = "3ra posicion ", sp4 = "4ta posicion", sp5 = "Primera posicion serie", sp6 = "2da posicion serie", sp7 = "3era posicion serie";
		for (int j = 0; j < posicion1.length; j++) {

			sp1 += "\n" + posicion1[j] + " veces Aparece:" + j;
			sp2 += "\n" + posicion2[j] + " veces Aparece:" + j;
			sp3 += "\n" + posicion3[j] + " veces Aparece:" + j;
			sp4 += "\n" + posicion4[j] + " veces Aparece:" + j;

			sp5 += "\n" + posicionserie1[j] + " veces Aparece:" + j;
			sp6 += "\n" + posicionserie2[j] + " veces Aparece:" + j;
			sp7 += "\n" + posicionserie3[j] + " veces Aparece:" + j;
		}

		System.out.println(sp1);
		System.out.println();
		System.out.println(sp2);
		System.out.println();
		System.out.println(sp3);
		System.out.println();
		System.out.println(sp4);
		System.out.println();
		System.out.println(sp5);
		System.out.println();
		System.out.println(sp6);
		System.out.println();
		System.out.println(sp7);

	}

	private void saveFile(String sorteo) {

		//
		// http://www.loteriademanizales.com/web2014/resultados.php?sorteo=4509&button=Consultar
		URL url;
		try {
			url = new URL(
					"http://www.loteriademanizales.com/web2014/resultados.php?sorteo="
							+ sorteo + "&button=Consultar");

			URLConnection urlconn = url.openConnection();
			inputS = urlconn.getInputStream();
			fos = new FileOutputStream("pages/" + sorteo + "file.html");
			byte bytes[] = new byte[200000];
			int a = inputS.read(bytes);
			while (a > 0) {
				fos.write(bytes, 0, a);
				a = inputS.read(bytes);
			}
			String s = new String(bytes);
			System.out.println("save sorteo :" + sorteo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
