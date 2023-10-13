package br.gov.cesarschool.poo.bonusvendas.tela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;


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
	private Button[] radios;
	private Button btnAdicionarAlterar;
	private Button btnNovoVendedor;
	private Button btnBuscar;
	private Combo comboBoxEstado;


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
		Text inputCpf = new Text(shell, SWT.BORDER);
		inputCpf.setBounds(168, 40, 93, 20);
		this.inputCpf = inputCpf;
		inputCpf.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                if (!newText.matches("^(\\d{0,3}(\\.\\d{0,3}(\\.\\d{0,3}(-\\d{0,2})?)?)?)?$")) {
                    e.doit = false; 
                }
            }
        });
		
		Label labelNomeCompleto = new Label(shell, SWT.NONE);
		labelNomeCompleto.setBounds(41, 70, 121, 20);
		labelNomeCompleto.setText("Nome Completo");
		Text inputNomeCompleto = new Text(shell, SWT.BORDER);
		inputNomeCompleto.setBounds(168, 75, 149, 20);
		inputNomeCompleto.setEnabled(false);
		this.inputNomeCompleto = inputNomeCompleto;
		
		Label labelSexo = new Label(shell, SWT.NONE);
		labelSexo.setBounds(41, 100, 121, 20);
		labelSexo.setText("Sexo");
		Button[] radios = new Button[2];
		this.radios = radios;
		radios[0] = new Button(shell, SWT.RADIO);
		radios[0].setSelection(true);
		radios[0].setText("Masculino");
		radios[0].setEnabled(false);
		radios[0].setBounds(168, 105, 121, 20);
		radios[1] = new Button(shell, SWT.RADIO);
		radios[1].setText("Feminino");
		radios[1].setBounds(168, 130, 121, 20);
		radios[1].setEnabled(false);
		
		Label labelDataDeNascimento = new Label(shell, SWT.NONE);
		labelDataDeNascimento.setBounds(41, 150, 121, 20);
		labelDataDeNascimento.setText("Data de Nascimento");
		Text inputDataDeNascimento = new Text(shell, SWT.BORDER);
		inputDataDeNascimento.setBounds(168, 155, 149, 20);
		inputDataDeNascimento.setEnabled(false);
		this.inputDataDeNascimento = inputDataDeNascimento;
		inputDataDeNascimento.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                if (!newText.matches("^\\d{0,2}(\\/\\d{0,2}(\\/\\d{0,4})?)?$")) {
                    e.doit = false; 
                }
            }
        });
		
		Label labelRenda = new Label(shell, SWT.NONE);
		labelRenda.setBounds(41, 180, 121, 20);
		labelRenda.setText("Renda");
		Text inputRenda = new Text(shell, SWT.BORDER);
		inputRenda.setBounds(168, 185, 149, 20);
		inputRenda.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                if (!newText.matches("^-?\\d+(\\.\\d*)?$")) {
                    e.doit = false; 
                }
            }
        });
		inputRenda.setEnabled(false);
		this.inputRenda = inputRenda;
	
		Label labelLogradouro = new Label(shell, SWT.NONE);
		labelLogradouro.setBounds(41, 210, 121, 20);
		labelLogradouro.setText("Logradouro");
		Text inputLogradouro = new Text(shell, SWT.BORDER);
		inputLogradouro.setBounds(168, 215, 149, 20);
		inputLogradouro.setEnabled(false);
		this.inputLogradouro = inputLogradouro;
		
		Label labelNumero = new Label(shell, SWT.NONE);
		labelNumero.setBounds(41, 240, 121, 20);
		labelNumero.setText("Número");
		Text inputNumero = new Text(shell, SWT.BORDER);
		inputNumero.setBounds(168, 245, 149, 20);
		inputNumero.setEnabled(false);
		inputNumero.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                if (!newText.matches("^-?\\d{1,7}$")) {
                    e.doit = false; 
                }
            }
        });
		this.inputNumero = inputNumero;
		
		Label labelComplemento = new Label(shell, SWT.NONE);
		labelComplemento.setBounds(41, 270, 121, 20);
		labelComplemento.setText("Complemento");
		Text inputComplemento = new Text(shell, SWT.BORDER);
		inputComplemento.setBounds(168, 275, 149, 20);
		inputComplemento.setEnabled(false);
		this.inputComplemento = inputComplemento;
		
		Label labelCep = new Label(shell, SWT.NONE);
		labelCep.setBounds(41, 300, 121, 20);
		labelCep.setText("CEP");
		Text inputCep = new Text(shell, SWT.BORDER);
		inputCep.setBounds(168, 305, 149, 20);
		inputCep.setEnabled(false);
		this.inputCep = inputCep;
		inputCep.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String currentText = ((Text) e.widget).getText();
                String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);

                if (!newText.matches("^\\d{0,2}(\\.\\d{0,3}(-\\d{0,3})?)?$")) {
                    e.doit = false;
                }
            }
        });
		
		Label labelCidade = new Label(shell, SWT.NONE);
		labelCidade.setBounds(41, 330, 121, 20);
		labelCidade.setText("Cidade");
		Text inputCidade = new Text(shell, SWT.BORDER);
		inputCidade.setBounds(168, 335, 149, 20);
		inputCidade.setEnabled(false);
		this.inputCidade = inputCidade;
		
		Label labelEstado = new Label(shell, SWT.NONE);
		labelEstado.setBounds(41, 360, 121, 20);
		labelEstado.setText("Estado");
	    Combo comboBoxEstado = new Combo(shell, SWT.READ_ONLY);
	    comboBoxEstado.setBounds(168, 365, 149, 20);
        String[] estados = {
            "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
            "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", 
            "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", 
            "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", 
            "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", 
            "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
        };
        comboBoxEstado.setItems(estados);
	    comboBoxEstado.setEnabled(false);
		this.comboBoxEstado = comboBoxEstado;
		
		Button btnAdicionarAlterar = new Button(shell, SWT.NONE);
		this.btnAdicionarAlterar = btnAdicionarAlterar;
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
		Button btnNovoVendedor = new Button(shell, SWT.NONE);
		this.btnNovoVendedor = btnNovoVendedor;
		
		btnAdicionarAlterar.setBounds(170, 400, 90, 30);
		btnAdicionarAlterar.setText("Incluir");
		btnAdicionarAlterar.setEnabled(false);
		btnAdicionarAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (inputCpf.getText() == null) {
					JOptionPane.showMessageDialog(null, "Formáto inválido do CPF.");
					return;
				}
				
				if (inputDataDeNascimento.getText() == null) {
					JOptionPane.showMessageDialog(null, "Prencha o campo de data de nascimento.");
					return;
				}
				
				String cpf = inputCpf.getText().replace(".", "").replace("-", "");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate dataDeNascimento = LocalDate.parse(inputDataDeNascimento.getText(), formatter);

				if (inputRenda.getText() == null) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de renda.");
					return;
				}
				
				if (inputNumero.getText() == null) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de número.");
					return;					
				}
				
				Endereco endereco = new Endereco(inputLogradouro.getText(), Integer.parseInt(inputNumero.getText()), inputComplemento.getText(), inputCep.getText(), inputCidade.getText(), comboBoxEstado.getText(), "Brasil");
				Vendedor vendedor = new Vendedor(cpf, inputNomeCompleto.getText(), radios[0].getSelection() == true ? Sexo.MASCULINO : Sexo.FEMININO, dataDeNascimento, Double.parseDouble(inputRenda.getText()), endereco);
				
				String msg = null;
				
				if (btnAdicionarAlterar.getText().equals("Incluir")) {
					ResultadoInclusaoVendedor resultadoInclusao = mediator.incluir(vendedor);
					
					msg = resultadoInclusao.getMensagemErroValidacao();
				} else {
					msg = mediator.alterar(vendedor);
				}
				
				if (msg != null) {
					JOptionPane.showMessageDialog(null, msg);	
				} else {
					inputCpf.setEnabled(true);
					inputNomeCompleto.setEnabled(false);
			        inputDataDeNascimento.setEnabled(false);
			        inputRenda.setEnabled(false);
			        radios[0].setEnabled(false);
			        radios[1].setEnabled(false);
			        inputLogradouro.setEnabled(false);
			        inputNumero.setEnabled(false);
			        inputComplemento.setEnabled(false);
			        inputCep.setEnabled(false);
			        inputCidade.setEnabled(false);
			        comboBoxEstado.setEnabled(false);
			        
			        inputCpf.setText("");
			        inputNomeCompleto.setText("");
			        inputDataDeNascimento.setText("");
			        inputRenda.setText("");
			        radios[0].setSelection(false);
			        radios[1].setSelection(false);
			        inputLogradouro.setText("");
			        inputNumero.setText("");
			        inputComplemento.setText("");
			        inputCep.setText("");
			        inputCidade.setText("");
			        comboBoxEstado.setText("");
					
			        btnNovoVendedor.setEnabled(true);
			        btnBuscar.setEnabled(true);
			        btnAdicionarAlterar.setEnabled(false);
					btnAdicionarAlterar.setText("Incluir");
				}
			}
		});
		
		btnNovoVendedor.setBounds(80, 400, 90, 30);
		btnNovoVendedor.setText("Novo");
		btnNovoVendedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {			
				if (inputCpf.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de CPF!");
					return;
				}
				
				String cpf = inputCpf.getText().replace(".", "").replace("-", "");
				
				Vendedor vendedor = mediator.buscar(cpf);
				
				if (vendedor != null) {
					JOptionPane.showMessageDialog(null, "Vendedor não encontrado.");
				} else {
					inputCpf.setEnabled(false);
					inputNomeCompleto.setEnabled(true);
			        inputDataDeNascimento.setEnabled(true);
			        inputRenda.setEnabled(true);
			        radios[0].setEnabled(true);
			        radios[1].setEnabled(true);
			        inputLogradouro.setEnabled(true);
			        inputNumero.setEnabled(true);
			        inputComplemento.setEnabled(true);
			        inputCep.setEnabled(true);
			        inputCidade.setEnabled(true);
			        comboBoxEstado.setEnabled(true);
					
					btnNovoVendedor.setEnabled(false);
					btnAdicionarAlterar.setEnabled(true);
					btnBuscar.setEnabled(false);
				}
			}
		});
		
		
		btnBuscar.setBounds(270, 400, 90, 30);
		btnBuscar.setText("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {			
				if (inputCpf.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de CPF!");
					return;
				}
				
				String cpf = inputCpf.getText().replace(".", "").replace("-", "");
				
				Vendedor vendedor = mediator.buscar(cpf);
				
				if (vendedor == null) {
					JOptionPane.showMessageDialog(null, "Vendedor não encontrado.");
				} else {
					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					
					inputNomeCompleto.setText(vendedor.getNomeCompleto());
			        inputDataDeNascimento.setText(vendedor.getDataNascimento().format(formatters));
			        inputRenda.setText(String.valueOf(vendedor.getRenda()));
			        
			        if (vendedor.getSexo().getCodigo() == 1) {
			        	radios[1].setSelection(true);
			        	radios[0].setSelection(false);
			        } else {
			        	radios[0].setSelection(true);
			        	radios[1].setSelection(false);
			        }
			        
			        inputLogradouro.setText(vendedor.getEndereco().getLogradouro());
			        inputNumero.setText(String.valueOf(vendedor.getEndereco().getNumero()));
			        inputComplemento.setText(vendedor.getEndereco().getComplemento());
			        inputCep.setText(vendedor.getEndereco().getCep());
			        inputCidade.setText(vendedor.getEndereco().getCidade());
			        comboBoxEstado.setText(vendedor.getEndereco().getEstado());

			        inputCpf.setEnabled(false);
			        inputNomeCompleto.setEnabled(true);
			        inputDataDeNascimento.setEnabled(true);
			        inputRenda.setEnabled(true);
			        radios[0].setEnabled(true);
			        radios[1].setEnabled(true);
			        inputLogradouro.setEnabled(true);
			        inputNumero.setEnabled(true);
			        inputComplemento.setEnabled(true);
			        inputCep.setEnabled(true);
			        inputCidade.setEnabled(true);
			        comboBoxEstado.setEnabled(true);
			        
					btnAdicionarAlterar.setEnabled(true);
					btnNovoVendedor.setEnabled(false);
					btnBuscar.setEnabled(false);
					btnAdicionarAlterar.setText("Alterar");

				}
			}
		});
	
	}
}
