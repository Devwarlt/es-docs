package exercicio01;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Person {
    // - name: String
    // Nome -> nome da pessoa.
    private String name;

    // - age: int
    // Idade -> idade da pessoa.
    private int age;

    // - targetFunds: float
    // Alvo -> valor em dinheiro em que a pessoa
    // quer aposentar.
    private float targetFunds;

    // - fees: float
    // Juros -> valor médio dos juros por ano que
    // será aplicado
    // no cálculo.
    private float fees;

    // - tax: float
    // Contribuição -> valor anual aplicado pela
    // pessoa.
    private float tax;

    // - taxLiftTime: int
    // Anos -> total de anos que será preciso a
    // pessoa realizar a sua contribuição.
    private int taxLiftTime;

    // - balance: float
    // Saldo -> valor acumulado ano a ano.
    private float balance;

    public Person() {
        super();
    }

    public void getInputInfo() {
        JOptionPane.showMessageDialog(null, "Preencha o formulário.");

        try {
            this.name = getJOptionInput(String.class, "Digite o nome:");
            this.age = getJOptionInput(Integer.class, "Digite a idade: ");
            this.targetFunds = getJOptionInput(Float.class, "Digite o alvo: ");
            this.fees = getJOptionInput(Float.class, "Digite os juros: ") / 100.0F;
            this.tax = getJOptionInput(Float.class, "Digite a contribuição: ");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        this.processContributions();
    }

    public void getFullInfo() {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuffer sb = new StringBuffer();
        sb.append("-== Informações da pessoa ==-");
        sb.append("\n\n");
        sb.append(String.format("> Nome: %s\n", this.name));
        sb.append(String.format("> Idade: %d\n", this.age));
        sb.append(String.format("> Alvo: $%s\n", df.format(this.targetFunds)));
        sb.append(String.format("> Juros: %s%%\n", df.format(this.fees * 100.0F)));
        sb.append(String.format("> Contribuição: $%s/ano\n", df.format(this.tax)));
        sb.append(String.format("> Anos: %d\n", this.taxLiftTime));
        sb.append(String.format("> Saldo: $%s\n", df.format(this.balance)));

        JOptionPane.showMessageDialog(null, sb);
    }

    private void processContributions() {
        while (this.balance < this.targetFunds) {
            this.balance = (this.balance + this.tax) * (1 + this.fees);
            this.taxLiftTime++;
        }
    }

    private static <T> T getJOptionInput(Class<T> type, String inputName) throws Exception {
        String outputStr = JOptionPane.showInputDialog(inputName);
        Object output;
        if (type == String.class)
            output = outputStr;
        else if (type == Integer.class)
            output = Integer.parseInt(outputStr);
        else if (type == Float.class)
            output = Float.parseFloat(outputStr);
        else
            throw new Exception(String.format("Type (class: %s) not implement!", type.getClass().getName()));
        return type.cast(output);
    }
}
