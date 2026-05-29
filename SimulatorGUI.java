import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SimulatorGUI extends JFrame {
    // COMPONENTS
    private JTextField populationField;
    private JTextField vaccineField;
    private JTextField connectionField;
    private JTextField infectedField;
    private JTextField probabilityField;
    private JTextField daysField;
    private JTextArea reportArea;
    private JTable comparisonTable;
    private DefaultTableModel tableModel;
    // COLORS
    private final Color backgroundColor =
            new Color(15, 23, 42);

    private final Color panelColor =
            new Color(30, 41, 59);

    private final Color accentColor =
            new Color(59, 130, 246);
    // CONSTRUCTOR
    public SimulatorGUI() {
        setTitle("Optimal Vaccination Strategy Simulator");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);
        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(accentColor);
        titlePanel.setPreferredSize(
                new Dimension(100, 70)
        );
        JLabel titleLabel = new JLabel(
                "Optimal Vaccination Strategy Simulator"
        );
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        // CONTROL PANEL
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(panelColor);
        controlPanel.setPreferredSize(
                new Dimension(320, 0)
        );
        controlPanel.setLayout(
                new GridLayout(20, 1, 10, 10)
        );
        // LABELS
        JLabel populationLabel =
                createLabel("Population Size");
        JLabel vaccineLabel =
                createLabel("Vaccines Available");
        JLabel connectionLabel =
                createLabel("Max Connections");
        JLabel infectedLabel =
                createLabel("Initial Infected");
        JLabel probabilityLabel =
                createLabel("Infection Probability");
        JLabel daysLabel =
                createLabel("Simulation Days");
        // TEXTFIELDS
        populationField = createTextField("");
        vaccineField = createTextField("");
        connectionField = createTextField("5");
        infectedField = createTextField("5");
        probabilityField = createTextField("0.3");
        daysField = createTextField("5");
        // BUTTON
        JButton runButton =
                new JButton("Run Simulation");
        runButton.setBackground(accentColor);
        runButton.setForeground(Color.WHITE);
        runButton.setFocusPainted(false);
        runButton.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );
        runButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        // ADD COMPONENTS
        controlPanel.add(new JLabel());
        controlPanel.add(populationLabel);
        controlPanel.add(populationField);
        controlPanel.add(vaccineLabel);
        controlPanel.add(vaccineField);
        controlPanel.add(connectionLabel);
        controlPanel.add(connectionField);
        controlPanel.add(infectedLabel);
        controlPanel.add(infectedField);
        controlPanel.add(probabilityLabel);
        controlPanel.add(probabilityField);
        controlPanel.add(daysLabel);
        controlPanel.add(daysField);
        controlPanel.add(new JLabel());
        controlPanel.add(runButton);
        add(controlPanel, BorderLayout.WEST);
        // CENTER PANEL
        JPanel centerPanel =
                new JPanel(new BorderLayout());
        centerPanel.setBackground(backgroundColor);
        // TABLE
        String[] columns = {
                "Strategy",
                "Vaccinated",
                "Infected",
                "Healthy",
                "Infection Rate"
        };
        tableModel =
                new DefaultTableModel(columns, 0);
        comparisonTable =
                new JTable(tableModel);
        comparisonTable.setRowHeight(35);
        comparisonTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        comparisonTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );
        comparisonTable.getTableHeader()
                .setBackground(accentColor);
        comparisonTable.getTableHeader()
                .setForeground(Color.WHITE);
        JScrollPane tableScroll =
                new JScrollPane(comparisonTable);
        centerPanel.add(tableScroll, BorderLayout.CENTER);
        // REPORT AREA
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setBackground(
                new Color(2, 6, 23)
        );
        reportArea.setForeground(Color.WHITE);
        reportArea.setFont(
                new Font("Consolas", Font.PLAIN, 15)
        );
        JScrollPane reportScroll =
                new JScrollPane(reportArea);
        reportScroll.setPreferredSize(
                new Dimension(100, 250)
        );
        centerPanel.add(reportScroll, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        // BUTTON ACTION
        runButton.addActionListener(
                e -> runSimulation()
        );
    }
    // CREATE LABEL
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );
        return label;
    }
    // CREATE TEXTFIELD
    private JTextField createTextField(String text) {
        JTextField field = new JTextField(text);
        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );
        return field;
    }
    // RUN SIMULATION
    private void runSimulation() {
        try {
            tableModel.setRowCount(0);
            reportArea.setText("");
            int populationSize =
                    Integer.parseInt(
                            populationField.getText()
                    );
            int vaccines =
                    Integer.parseInt(
                            vaccineField.getText()
                    );
            int maxConnections =
                    Integer.parseInt(
                            connectionField.getText()
                    );
            int initialInfected =
                    Integer.parseInt(
                            infectedField.getText()
                    );
            double infectionProbability =
                    Double.parseDouble(
                            probabilityField.getText()
                    );
            int days =
                    Integer.parseInt(
                            daysField.getText()
                    );
            // VALIDATION
            if(vaccines > populationSize) {
                JOptionPane.showMessageDialog(
                        this,
                        "Vaccines cannot exceed population size."
                );
                return;
            }
            if(initialInfected > populationSize) {
                JOptionPane.showMessageDialog(
                        this,
                        "Initial infected exceeds population."
                );
                return;
            }
            if(infectionProbability < 0 ||
                    infectionProbability > 1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Probability must be between 0 and 1."
                );
                return;
            }
            // CREATE POPULATIONS
            java.util.List<Person> greedyPopulation =
                    DataGenerator.generatePopuation(
                            populationSize
                    );
            java.util.List<Person> randomPopulation =
                    DataGenerator.generatePopuation(
                            populationSize
                    );
            java.util.List<Person> degreePopulation =
                    DataGenerator.generatePopuation(
                            populationSize
                    );
            // BUILD GRAPH
            GraphBuilder.buildGraph(
                    greedyPopulation,
                    maxConnections
            );
            GraphBuilder.buildGraph(
                    randomPopulation,
                    maxConnections
            );
            GraphBuilder.buildGraph(
                    degreePopulation,
                    maxConnections
            );
            // INITIAL INFECTION
            DataGenerator.infectinitial(
                    greedyPopulation,
                    initialInfected
            );
            DataGenerator.infectinitial(
                    randomPopulation,
                    initialInfected
            );
            DataGenerator.infectinitial(
                    degreePopulation,
                    initialInfected
            );
            // APPLY STRATEGIES
            Strategy.greedyvaccination(
                    greedyPopulation,
                    vaccines
            );
            Strategy.randomvaccination(
                    randomPopulation,
                    vaccines
            );
            Strategy.degreevaccination(
                    degreePopulation,
                    vaccines
            );
            // RUN SIMULATION
            Infectionsimulator.spread(
                    greedyPopulation,
                    infectionProbability,
                    days
            );
            Infectionsimulator.spread(
                    randomPopulation,
                    infectionProbability,
                    days
            );
            Infectionsimulator.spread(
                    degreePopulation,
                    infectionProbability,
                    days
            );
            // RESULTS
            int greedyInfected =
                    SimulationResult.totalInfected(
                            greedyPopulation
                    );
            int randomInfected =
                    SimulationResult.totalInfected(
                            randomPopulation
                    );
            int degreeInfected =
                    SimulationResult.totalInfected(
                            degreePopulation
                    );
            int greedyHealthy =
                    SimulationResult.healthyPeople(
                            greedyPopulation
                    );
            int randomHealthy =
                    SimulationResult.healthyPeople(
                            randomPopulation
                    );
            int degreeHealthy =
                    SimulationResult.healthyPeople(
                            degreePopulation
                    );
            double greedyRate =
                    ((double) greedyInfected /
                            populationSize) * 100;
            double randomRate =
                    ((double) randomInfected /
                            populationSize) * 100;
            double degreeRate =
                    ((double) degreeInfected /
                            populationSize) * 100;
            // TABLE
            tableModel.addRow(new Object[]{
                    "Greedy",
                    vaccines,
                    greedyInfected,
                    greedyHealthy,
                    String.format("%.2f%%", greedyRate)
            });
            tableModel.addRow(new Object[]{
                    "Random",
                    vaccines,
                    randomInfected,
                    randomHealthy,
                    String.format("%.2f%%", randomRate)
            });
            tableModel.addRow(new Object[]{
                    "Degree-Based",
                    vaccines,
                    degreeInfected,
                    degreeHealthy,
                    String.format("%.2f%%", degreeRate)
            });
            // BEST STRATEGY
            String bestStrategy = "Greedy";
            double bestRate = greedyRate;
            if(randomRate < bestRate) {
                bestRate = randomRate;
                bestStrategy = "Random";
            }
            if(degreeRate < bestRate) {
                bestRate = degreeRate;
                bestStrategy = "Degree-Based";
            }
            // REPORT
            reportArea.append(
                    "========== FINAL REPORT ==========\n\n"
            );
            reportArea.append(
                    "Population Size : "
                            + populationSize + "\n"
            );
            reportArea.append(
                    "Vaccines Used   : "
                            + vaccines + "\n"
            );
            reportArea.append(
                    "Max Connections : "
                            + maxConnections + "\n"
            );
            reportArea.append(
                    "Initial Infected: "
                            + initialInfected + "\n"
            );
            reportArea.append(
                    "Infection Probability : "
                            + infectionProbability + "\n"
            );
            reportArea.append(
                    "Simulation Days : "
                            + days + "\n\n"
            );
            reportArea.append(
                    "Greedy Infection Rate       : "
                            + String.format("%.2f%%", greedyRate)
                            + "\n"
            );
            reportArea.append(
                    "Random Infection Rate       : "
                            + String.format("%.2f%%", randomRate)
                            + "\n"
            );
            reportArea.append(
                    "Degree-Based Infection Rate : "
                            + String.format("%.2f%%", degreeRate)
                            + "\n\n"
            );
            reportArea.append(
                    "Best Strategy : "
                            + bestStrategy + "\n"
            );
            reportArea.append(
                    "Lowest Infection Rate : "
                            + String.format("%.2f%%", bestRate)
                            + "\n\n"
            );
            reportArea.append(
                    "Simulation Completed Successfully."
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimulatorGUI().setVisible(true);
        });
    }
}