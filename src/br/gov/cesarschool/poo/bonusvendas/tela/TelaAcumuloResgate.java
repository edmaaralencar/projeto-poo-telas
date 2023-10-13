package br.gov.cesarschool.poo.bonusvendas.tela;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import org.eclipse.swt.widgets.Menu;

public class TelaAcumuloResgate {
	protected Shell shell;
	private static AcumuloResgateMediator mediator = new AcumuloResgateMediator();
	private CaixaDeBonusDAO caixaDeBonusDAO = new CaixaDeBonusDAO();
	private Text inputCaixaDeBonus;
	private Text inputSaldo;
	private Text inputValor;
	private Combo comboBoxTipoDeResgate;
	private Button btnBuscar;
	private Button btnVoltar;
	private Button btnAcumularResgatar;
	private Button[] operacaoRadios;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaAcumuloResgate window = new TelaAcumuloResgate();
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
		shell.setSize(450, 500);
		shell.setText("SWT Application");

		Label labelCaixaDeBonus = new Label(shell, SWT.NONE);
		labelCaixaDeBonus.setBounds(41, 40, 121, 20);
		labelCaixaDeBonus.setText("Caixa de bônus");
		Text inputCaixaDeBonus = new Text(shell, SWT.BORDER);
		inputCaixaDeBonus.setBounds(168, 40, 93, 20);
		this.inputCaixaDeBonus = inputCaixaDeBonus;
		
		Label labelOperacao = new Label(shell, SWT.NONE);
		labelOperacao.setBounds(41, 100, 121, 20);
		labelOperacao.setText("Operação");
		Button[] operacaoRadios = new Button[3];
		this.operacaoRadios = operacaoRadios;
		operacaoRadios[0] = new Button(shell, SWT.RADIO);
		operacaoRadios[0].setText("Acumular");
		operacaoRadios[0].setEnabled(true);
		operacaoRadios[0].setBounds(168, 105, 121, 20);
		operacaoRadios[1] = new Button(shell, SWT.RADIO);
		operacaoRadios[1].setText("Resgatar");
		operacaoRadios[1].setBounds(168, 130, 121, 20);
		operacaoRadios[1].setEnabled(true);
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
		Button btnAcumularResgatar = new Button(shell, SWT.NONE);
		this.btnAcumularResgatar = btnAcumularResgatar;
		Button btnVoltar = new Button(shell, SWT.NONE);
		this.btnVoltar = btnVoltar;
		
		btnBuscar.setBounds(170, 180, 90, 30);
		btnBuscar.setText("Buscar");
		btnBuscar.setEnabled(true);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (inputCaixaDeBonus.getText() == null) {
					JOptionPane.showInternalMessageDialog(null, "Preencha o campo de caixa de bônus.");
					return;
				}
								
				CaixaDeBonus caixaDeBonus = caixaDeBonusDAO.buscar(Long.parseLong(inputCaixaDeBonus.getText()));
				
				if (caixaDeBonus == null) {
					JOptionPane.showInternalMessageDialog(null, "Caixa de bônus não existe.");
				} else {
					inputCaixaDeBonus.setEnabled(false);
					operacaoRadios[0].setEnabled(false);
					operacaoRadios[1].setEnabled(false);
					btnBuscar.setEnabled(false);
					inputSaldo.setEnabled(true);
					inputValor.setEnabled(true);
					
					inputSaldo.setText(String.valueOf(caixaDeBonus.getSaldo()));
					
					btnAcumularResgatar.setEnabled(true);
					btnVoltar.setEnabled(true);
					
					if (operacaoRadios[0].getSelection() == true) {
						btnAcumularResgatar.setText("Acumular");
					} else {	
						btnAcumularResgatar.setText("Resgatar");
						comboBoxTipoDeResgate.setEnabled(true);
					}
				}
				
			}
		});
		
		Label labelSaldo = new Label(shell, SWT.NONE);
		labelSaldo.setBounds(41, 220, 121, 20);
		labelSaldo.setText("Saldo");
		Text inputSaldo = new Text(shell, SWT.BORDER);
		inputSaldo.setEnabled(false);
		inputSaldo.setBounds(168, 220, 93, 20);
		this.inputSaldo = inputSaldo;

		Label labelTipoDeResgate = new Label(shell, SWT.NONE);
		labelTipoDeResgate.setBounds(41, 250, 121, 20);
		labelTipoDeResgate.setText("Tipo de resgate");
	    Combo comboBoxTipoDeResgate = new Combo(shell, SWT.READ_ONLY);
	    comboBoxTipoDeResgate.setBounds(168, 250, 93, 20);
	    String items[] = { "CASH", "SERVIÇO", "PRODUTO"};
	    comboBoxTipoDeResgate.setItems(items);
	    comboBoxTipoDeResgate.setEnabled(false);
	    this.comboBoxTipoDeResgate = comboBoxTipoDeResgate;
	    
		Label labelValor = new Label(shell, SWT.NONE);
		labelValor.setBounds(41, 280, 121, 20);
		labelValor.setText("Valor");
		Text inputValor = new Text(shell, SWT.BORDER);
		inputValor.setEnabled(false);
		inputValor.setBounds(168, 280, 93, 20);
		inputValor.addVerifyListener(new VerifyListener() {
		    @Override
		    public void verifyText(VerifyEvent e) {
		        String currentText = ((Text)e.widget).getText();
		        String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

		        if (!newText.matches("\\d{0,2}(\\.\\d{0,2})?")) {
		            e.doit = false; 
		        }
		    }
		});
		this.inputValor = inputValor;
		
				
		btnAcumularResgatar.setBounds(170, 320, 90, 30);
		btnAcumularResgatar.setText("Acumular");
		btnAcumularResgatar.setEnabled(false);
		btnAcumularResgatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {					
				if (inputValor.getText().length() == 0 || inputSaldo.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Preencha os campos!");
					return;
				}
				
				double valorValidado = Double.parseDouble(inputValor.getText());
								
				if (operacaoRadios[0].getSelection() == true) {
					
					String operacaoAcumularBonus = mediator.acumularBonus(Long.parseLong(inputCaixaDeBonus.getText()), valorValidado);
					
					if (operacaoAcumularBonus == null) {
						JOptionPane.showMessageDialog(null, "Operação de acumular concluída com sucesso!");	
						inputCaixaDeBonus.setText("");
						inputSaldo.setText("");
						inputValor.setText("");
						
						comboBoxTipoDeResgate.setText("");
						operacaoRadios[0].setSelection(false);
						operacaoRadios[1].setSelection(false);
						
						inputCaixaDeBonus.setEnabled(true);
						operacaoRadios[0].setEnabled(true);
						operacaoRadios[1].setEnabled(true);
						comboBoxTipoDeResgate.setEnabled(false);
						inputSaldo.setEnabled(false);
						inputValor.setEnabled(false);
						
						btnBuscar.setEnabled(true);
						btnVoltar.setEnabled(false);
						btnAcumularResgatar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, operacaoAcumularBonus);	
					}
				} else {
					TipoResgate tipo = null;
					
					if (comboBoxTipoDeResgate.getText() == "CASH") {
						tipo = TipoResgate.CASH;
					} else if (comboBoxTipoDeResgate.getText() == "PRODUTO") {
						tipo = TipoResgate.PRODUTO;
					} else {
						tipo = TipoResgate.SERVICO;
					}
					
					String operacaoResgatarBonus = mediator.resgatar(Long.parseLong(inputCaixaDeBonus.getText()), valorValidado, tipo);
					
					if (operacaoResgatarBonus == null) {
						JOptionPane.showMessageDialog(null, "Operação de resgatar concluída com sucesso!");	
						inputCaixaDeBonus.setText("");
						inputSaldo.setText("");
						inputValor.setText("");
						
						comboBoxTipoDeResgate.setText("");
						operacaoRadios[0].setSelection(false);
						operacaoRadios[1].setSelection(false);
						
						inputCaixaDeBonus.setEnabled(true);
						operacaoRadios[0].setEnabled(true);
						operacaoRadios[1].setEnabled(true);
						comboBoxTipoDeResgate.setEnabled(false);
						inputSaldo.setEnabled(false);
						inputValor.setEnabled(false);
						
						btnBuscar.setEnabled(true);
						btnVoltar.setEnabled(false);
						btnAcumularResgatar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, operacaoResgatarBonus);	
					}
				}
			}
		});
		
		btnVoltar.setBounds(80, 320, 90, 30);
		btnVoltar.setText("Voltar");
		btnVoltar.setEnabled(false);
		
		Menu menu = new Menu(shell);
		shell.setMenu(menu);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {	
				inputCaixaDeBonus.setText("");
				inputSaldo.setText("");
				inputValor.setText("");
				
				comboBoxTipoDeResgate.setText("");
				operacaoRadios[0].setSelection(false);
				operacaoRadios[1].setSelection(false);
				
				inputCaixaDeBonus.setEnabled(true);
				operacaoRadios[0].setEnabled(true);
				operacaoRadios[1].setEnabled(true);
				comboBoxTipoDeResgate.setEnabled(false);
				inputSaldo.setEnabled(false);
				inputValor.setEnabled(false);
				
				btnBuscar.setEnabled(true);
				btnVoltar.setEnabled(false);
				btnAcumularResgatar.setEnabled(false);
			}
		});		
	}
}
