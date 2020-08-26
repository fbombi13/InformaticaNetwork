import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class GraphBuilder {

    private static final String STYLE = "url('file:src/main/resources/Style.css')";
    private final Graph graph;
    private JPanel panel;
    private ViewPanel viewPanel;
    private final JFrame frame;

    public GraphBuilder(String graphName) {
        graph = new SingleGraph(graphName);
        graph.addAttribute("ui.stylesheet", STYLE);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        frame = new JFrame(graphName);
    }

    public GraphBuilder addEdge(String firstNode, String secondNode, String relation) {
        graph.addEdge(firstNode + secondNode, firstNode, secondNode).addAttribute("label", relation);
        return this;
    }

    public GraphBuilder addSubEdge(String firstNode, String secondNode, String relation) {
        graph.addNode(secondNode).addAttribute("ui.class", "subNode");
        addEdge(firstNode, secondNode, relation);
        return this;
    }

    public void generatePanel() {
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1380, 720);
        frame.setPreferredSize(new Dimension(700, 500));
        panel = new JPanel();
        panel.setLayout(new GridLayout());
        frame.add(panel);
    }

    public void addGraphIntoPanel() {
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        viewPanel = viewer.addDefaultView(false);
        Rectangle rec = panel.getBounds();
        viewPanel.setBounds(0, 0, rec.width, rec.height);
        viewPanel.setPreferredSize(new Dimension(rec.width, rec.height));
        panel.add(viewPanel);
    }

    public void mouseListener() {
        viewPanel.addMouseWheelListener(mwe -> zoomGraphMouseWheelMoved(mwe, viewPanel));
        frame.setVisible(true);
    }

    public void addingNodeName() {
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    private void zoomGraphMouseWheelMoved(MouseWheelEvent mwe, ViewPanel viewPanel) {
        if (Event.ALT_MASK != 0) {
            if (mwe.getWheelRotation() > 0) {
                double new_view_percent = viewPanel.getCamera().getViewPercent() + 0.05;
                viewPanel.getCamera().setViewPercent(new_view_percent);
            } else if (mwe.getWheelRotation() < 0) {
                double current_view_percent = viewPanel.getCamera().getViewPercent();
                if (current_view_percent > 0.05) {
                    viewPanel.getCamera().setViewPercent(current_view_percent - 0.05);
                }
            }
        }
    }
}
