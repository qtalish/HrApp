package com.kgate.util;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.kgate.controllers.Abcd;
import com.kgate.controllers.UserController;
import com.kgate.repository.AttendanceRepository;

public class MultipleLinesChart extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Abcd> ab;

	public MultipleLinesChart(List<Abcd> ab) { // the constructor will contain the panel of a certain size and the close
									// operations
		super("XY Line Chart Example with JFreechart"); // calls the super class constructor
	    this.ab = ab;
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);

		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	

	private JPanel createChartPanel() { // this method will create the chart panel containin the graph
		String chartTitle = "Attendance Chart";
		String xAxisLabel = "Months";
		String yAxisLabel = "Days";

		XYDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

		customizeChart(chart);

		// saves the chart as an image files
		File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;

		try {
			ChartUtils.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return new ChartPanel(chart);
	}

	private XYDataset createDataset() { // this method creates the data as time series
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries present = new XYSeries("Present");
		XYSeries absent = new XYSeries("Absent");
		XYSeries halfDays = new XYSeries("Half Days");
	
		absent.add(2.0, 1.0);
		absent.add(2.5, 2.4);
		absent.add(3.2, 3.2);
		absent.add(3.9, 4.8);
		absent.add(4.6, 1.0);

		halfDays.add(1.2, 4.0);
		halfDays.add(2.5, 4.4);
		halfDays.add(3.8, 4.2);
		halfDays.add(4.3, 3.8);
		halfDays.add(4.5, 4.0);

		dataset.addSeries(present);
		dataset.addSeries(absent);
		dataset.addSeries(halfDays);

		return dataset;
	}

	private void customizeChart(JFreeChart chart) { // here we make some customization
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));

		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(4.0f));

		// sets renderer for lines
		plot.setRenderer(renderer);

		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);

		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

	}

}