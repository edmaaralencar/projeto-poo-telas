package projetopoo.br.gov.cesarschool.poo.bonusvendas.tela;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TelaAdicao {
	protected Shell shell;
	private Text txtPrimeiroNumero;
	private Text txtSegundoNUmero;
	private Text txtResultado;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaAdicao window = new TelaAdicao();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(556, 330);
		shell.setText("Tela adi\u00E7\u00E3o");
		
		Label lblPrimeiroNmero = new Label(shell, SWT.NONE);
		lblPrimeiroNmero.setBounds(41, 40, 121, 20);
		lblPrimeiroNmero.setText("Primeiro n\u00FAmero");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(41, 102, 121, 20);
		lblNewLabel.setText("Segundo n\u00FAmero");
		
		txtPrimeiroNumero = new Text(shell, SWT.BORDER);
		txtPrimeiroNumero.setBounds(168, 40, 93, 26);
		
		txtSegundoNUmero = new Text(shell, SWT.BORDER);
		txtSegundoNUmero.setBounds(183, 102, 78, 26);
		
		Label lblResultado = new Label(shell, SWT.NONE);
		lblResultado.setBounds(41, 163, 70, 20);
		lblResultado.setText("Resultado");
		
		txtResultado = new Text(shell, SWT.BORDER);
		txtResultado.setEnabled(false);
		txtResultado.setEditable(false);
		txtResultado.setBounds(183, 163, 78, 37);
		
		Button btnSomar = new Button(shell, SWT.NONE);
		btnSomar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
				double n2 = Double.parseDouble(txtSegundoNUmero.getText()); 
				double soma = n1 + n2;
				txtResultado.setText("" + soma);	
			}
		});
		btnSomar.setBounds(130, 220, 90, 30);
		btnSomar.setText("Somar");
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtPrimeiroNumero.setText("");
				txtSegundoNUmero.setText("");
				txtResultado.setText("");
			}
		});
		btnLimpar.setBounds(254, 220, 90, 30);
		btnLimpar.setText("Limpar");

	}
}
