package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

// Referenced from the AlarmSystem
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class FitnessAppGUI extends JFrame{

    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String FILE_DESCRIPTOR = "...file";
	private static final String SCREEN_DESCRIPTOR = "...screen";
	// private AlarmController ac;
	// private KeyPad kp;
	private JComboBox<String> printCombo;
	private JDesktopPane desktop;
	private JInternalFrame controlPanel;

    private List<FitnessPlan> fitnessLog;
    private Scanner input;
    private FitnessPlan currFitnessPlan;
    private Exercise currExercise;

    private static final String JSON_STORE = "./data/fitnessplans.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
	 * Constructor sets up button panel, key pad and visual alarm status window.
	 */
	public FitnessAppGUI() {
		
	}
	


}
