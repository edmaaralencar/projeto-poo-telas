package projetopoo.br.gov.cesarschool.poo.bonusvendas.tela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import projetopoo.br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;
import projetopoo.br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import projetopoo.br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import projetopoo.br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import projetopoo.br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;


public class TelaManutencaoVendedor {
	protected Shell shell;
	private static VendedorMediator mediator = new VendedorMediator();	
	private Text inputCpf;
	private Text inputNomeCompleto;
	private Text inputDataDeNascimento;
	private Text inputRenda;
	private Text inputLogradouro;
	private Text inputNumero;
	private Text inputComplemento;
	private Text inputCep;
	private Text inputCidade;
	private Text inputEstado;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaManutencaoVendedor window = new TelaManutencaoVendedor();
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
		shell.setSize(556, 550);
		shell.setText("Tela manutencao do vendedor");
		
		Label labelCpf = new Label(shell, SWT.NONE);
		labelCpf.setBounds(41, 40, 121, 20);
		labelCpf.setText("CPF");
		inputCpf = new Text(shell, SWT.BORDER);
		inputCpf.setBounds(168, 40, 93, 20);
		
		inputCpf.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                // Get the current text and proposed new text
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                // Check if the new text matches the CPF format with three digits in each segment
                if (!newText.matches("^(\\d{0,3}(\\.\\d{0,3}(\\.\\d{0,3}(-\\d{0,2})?)?)?)?$")) {
                    e.doit = false; // Prevent invalid input
                }
            }
        });
		
		Label labelNomeCompleto = new Label(shell, SWT.NONE);
		labelNomeCompleto.setBounds(41, 70, 121, 20);
		labelNomeCompleto.setText("Nome Completo");
		inputNomeCompleto = new Text(shell, SWT.BORDER);
		inputNomeCompleto.setBounds(168, 75, 149, 20);
		
		Label labelSexo = new Label(shell, SWT.NONE);
		labelSexo.setBounds(41, 100, 121, 20);
		labelSexo.setText("Sexo");
		Button[] radios = new Button[2];
		radios[0] = new Button(shell, SWT.RADIO);
		radios[0].setSelection(true);
		radios[0].setText("Masculino");
		radios[0].setBounds(168, 105, 121, 20);
		radios[1] = new Button(shell, SWT.RADIO);
		radios[1].setText("Feminino");
		radios[1].setBounds(168, 130, 121, 20);
		
		Label labelDataDeNascimento = new Label(shell, SWT.NONE);
		labelDataDeNascimento.setBounds(41, 150, 121, 20);
		labelDataDeNascimento.setText("Data de Nascimento");
		inputDataDeNascimento = new Text(shell, SWT.BORDER);
		inputDataDeNascimento.setBounds(168, 155, 149, 20);
		inputDataDeNascimento.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                // Get the current text and proposed new text
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                // Check if the new text matches the date format "99/99/9999"
                if (!newText.matches("^\\d{0,2}(\\/\\d{0,2}(\\/\\d{0,4})?)?$")) {
                    e.doit = false; // Prevent invalid input
                }
            }
        });
		
		Label labelRenda = new Label(shell, SWT.NONE);
		labelRenda.setBounds(41, 180, 121, 20);
		labelRenda.setText("Renda");
		inputRenda = new Text(shell, SWT.BORDER);
		inputRenda.setBounds(168, 185, 149, 20);
		
		Label labelLogradouro = new Label(shell, SWT.NONE);
		labelLogradouro.setBounds(41, 210, 121, 20);
		labelLogradouro.setText("Logradouro");
		inputLogradouro = new Text(shell, SWT.BORDER);
		inputLogradouro.setBounds(168, 215, 149, 20);

		Label labelNumero = new Label(shell, SWT.NONE);
		labelNumero.setBounds(41, 240, 121, 20);
		labelNumero.setText("Número");
		inputNumero = new Text(shell, SWT.BORDER);
		inputNumero.setBounds(168, 245, 149, 20);
		
		Label labelComplemento = new Label(shell, SWT.NONE);
		labelComplemento.setBounds(41, 270, 121, 20);
		labelComplemento.setText("Complemento");
		inputComplemento = new Text(shell, SWT.BORDER);
		inputComplemento.setBounds(168, 275, 149, 20);
		
		Label labelCep = new Label(shell, SWT.NONE);
		labelCep.setBounds(41, 300, 121, 20);
		labelCep.setText("CEP");
		inputCep = new Text(shell, SWT.BORDER);
		inputCep.setBounds(168, 305, 149, 20);
		inputCep.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                // Get the current text and proposed new text
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                // Check if the new text matches the custom format "99.999-999"
                if (!newText.matches("^\\d{0,2}(\\.\\d{0,3}(-\\d{0,3})?)?$")) {
                    e.doit = false; // Prevent invalid input
                }
            }
        });
		
		Label labelCidade = new Label(shell, SWT.NONE);
		labelCidade.setBounds(41, 330, 121, 20);
		labelCidade.setText("Cidade");
		inputCidade = new Text(shell, SWT.BORDER);
		inputCidade.setBounds(168, 335, 149, 20);
		
		Label labelEstado = new Label(shell, SWT.NONE);
		labelEstado.setBounds(41, 360, 121, 20);
		labelEstado.setText("Estado");
		inputEstado = new Text(shell, SWT.BORDER);
		inputEstado.setBounds(168, 365, 149, 20);
		
		
		Button btnAdicionarAlterar = new Button(shell, SWT.NONE);
		btnAdicionarAlterar.setBounds(130, 400, 90, 30);
		btnAdicionarAlterar.setText("Incluir");
		btnAdicionarAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String cpf = inputCpf.getText().replace(".", "").replace("-", "");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate dataDeNascimento = LocalDate.parse(inputDataDeNascimento.getText(), formatter);

				Endereco endereco = new Endereco(inputLogradouro.getText(), Integer.parseInt(inputNumero.getText()), inputComplemento.getText(), inputCep.getText(), inputCidade.getText(), inputEstado.getText(), "Brasil");
				Vendedor vendedor = new Vendedor(cpf, inputNomeCompleto.getText(), radios[0].getSelection() == true ? Sexo.masculino : Sexo.feminino, dataDeNascimento, Double.parseDouble(inputRenda.getText()), endereco);
				
				
				String msg = null;
				
				if (btnAdicionarAlterar.getText().equals("Incluir")) {
					ResultadoInclusaoVendedor resultadoInclusao = mediator.incluir(vendedor);
					
					msg = resultadoInclusao.getMensagemErroValidacao();
				} else {
					msg = mediator.alterar(vendedor);
				}
				
				if (msg != null) {
					JOptionPane.showMessageDialog(null, 
							msg);	
				} else {
					btnAdicionarAlterar.setText("Incluir");
				}
			}
		});
	}
}
