import processing.core.PApplet;
import java.util.HashMap;

public class Pathfinding extends PApplet{

    HashMap<Vector2D, Node> gridNodes = new HashMap<>();
    HashMap<Vector2D, Node> activeSet = new HashMap<>();
    Vector2D coors[][];
    int xNodes, yNodes;
    Node currentNode, startNode, endNode;

    public static void main(String[] args) {
        PApplet.main("Pathfinding");
    }

    public void settings(){
        size(600, 600);
        xNodes = 50;
        yNodes = 50;
        coors = new Vector2D[xNodes][yNodes];
    }

    public void draw(){
        background(0);
        drawGrid();
        handleInput();
    }

    private void drawGrid(){
        final int NODE_X_SIZE = width / xNodes;
        final int NODE_Y_SIZE = height / yNodes;
        rectMode(CENTER);

        for (int i = 0; i < xNodes; i++) {
            for (int j = 0; j < yNodes; j++) {
                fill(255);

                int x = (NODE_X_SIZE / 2) + (i * NODE_X_SIZE);
                int y = (NODE_Y_SIZE / 2) + (j * NODE_Y_SIZE);

                if (coors[i][j] == null) {
                    coors[i][j] = new Vector2D(x, y);
                }

                if(gridNodes.size() == xNodes * yNodes){
                    if (gridNodes.containsKey(coors[i][j])) {
                        if (gridNodes.get(coors[i][j]).isInPath()) {
                            fill(150, 0, 150);
                        } else if (gridNodes.get(coors[i][j]).isStart()) {
                            fill(0, 0, 255);
                        } else if (gridNodes.get(coors[i][j]).isEnd()) {
                            fill(255, 0, 0);
                        } else if (gridNodes.get(coors[i][j]).isChecked()) {
                            fill(0, 100, 100);
                        } else if (gridNodes.get(coors[i][j]).isWall()) {
                            fill(50);
                        } else if (gridNodes.get(coors[i][j]).isActive()) {
                            fill(0, 255, 0);
                        } else if (gridNodes.get(coors[i][j]).isCurrNode()) {
                            fill(100, 100, 0);
                        }
                    }
                }

                rect(x, y, NODE_X_SIZE - (NODE_X_SIZE / 10f), NODE_Y_SIZE - (NODE_Y_SIZE / 10f));
                if (!gridNodes.containsKey(coors[i][j])){
                    gridNodes.put(coors[i][j], new Node(coors[i][j], new Vector2D(NODE_X_SIZE, NODE_Y_SIZE)));
                }
            }
        }

        rectMode(CORNER);
    }

    public void handleInput(){
        if (mousePressed) {
            if (key == 's') {
                for (Node mouseNode : gridNodes.values()) {
                    if (mouseX > mouseNode.getNodePosition().getxPos() - (mouseNode.getNodeSize().getxPos() / 2)) {
                        if (mouseX < mouseNode.getNodePosition().getxPos() + mouseNode.getNodeSize().getxPos() / 2) {
                            if (mouseY > mouseNode.getNodePosition().getyPos() - mouseNode.getNodeSize().getyPos() / 2) {
                                if (mouseY < mouseNode.getNodePosition().getyPos() + mouseNode.getNodeSize().getyPos() / 2) {
                                    if (gridNodes.containsKey(mouseNode.getNodePosition())) {
                                        gridNodes.get(mouseNode.getNodePosition()).setStart(true);
                                        startNode = mouseNode;
                                    }
                                } else {
                                    gridNodes.get(mouseNode.getNodePosition()).setStart(false);
                                }
                            } else {
                                gridNodes.get(mouseNode.getNodePosition()).setStart(false);
                            }
                        } else {
                            gridNodes.get(mouseNode.getNodePosition()).setStart(false);
                        }
                    } else {
                        gridNodes.get(mouseNode.getNodePosition()).setStart(false);
                    }
                }
            }

            if (key == 'e') {
                for (Node mouseNode : gridNodes.values()) {
                    if (mouseX > mouseNode.getNodePosition().getxPos() - (mouseNode.getNodeSize().getxPos() / 2)) {
                        if (mouseX < mouseNode.getNodePosition().getxPos() + mouseNode.getNodeSize().getxPos() / 2) {
                            if (mouseY > mouseNode.getNodePosition().getyPos() - mouseNode.getNodeSize().getyPos() / 2) {
                                if (mouseY < mouseNode.getNodePosition().getyPos() + mouseNode.getNodeSize().getyPos() / 2) {
                                    if (gridNodes.containsKey(mouseNode.getNodePosition())) {
                                        gridNodes.get(mouseNode.getNodePosition()).setEnd(true);
                                        endNode = mouseNode;
                                    }
                                } else {
                                    gridNodes.get(mouseNode.getNodePosition()).setEnd(false);
                                }
                            } else {
                                gridNodes.get(mouseNode.getNodePosition()).setEnd(false);
                            }
                        } else {
                            gridNodes.get(mouseNode.getNodePosition()).setEnd(false);
                        }
                    } else {
                        gridNodes.get(mouseNode.getNodePosition()).setEnd(false);
                    }
                }
            }

            if (key == 'b') {
                for (Node mouseNode : gridNodes.values()) {
                    if (mouseX > mouseNode.getNodePosition().getxPos() - (mouseNode.getNodeSize().getxPos() / 2)) {
                        if (mouseX < mouseNode.getNodePosition().getxPos() + mouseNode.getNodeSize().getxPos() / 2) {
                            if (mouseY > mouseNode.getNodePosition().getyPos() - mouseNode.getNodeSize().getyPos() / 2) {
                                if (mouseY < mouseNode.getNodePosition().getyPos() + mouseNode.getNodeSize().getyPos() / 2) {
                                    if (gridNodes.containsKey(mouseNode.getNodePosition())) {
                                        gridNodes.get(mouseNode.getNodePosition()).setWall(true);
                                        endNode = mouseNode;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (key == 'u') {
                for (Node mouseNode : gridNodes.values()) {
                    if (mouseX > mouseNode.getNodePosition().getxPos() - (mouseNode.getNodeSize().getxPos() / 2)) {
                        if (mouseX < mouseNode.getNodePosition().getxPos() + mouseNode.getNodeSize().getxPos() / 2) {
                            if (mouseY > mouseNode.getNodePosition().getyPos() - mouseNode.getNodeSize().getyPos() / 2) {
                                if (mouseY < mouseNode.getNodePosition().getyPos() + mouseNode.getNodeSize().getyPos() / 2) {
                                    if (gridNodes.containsKey(mouseNode.getNodePosition())) {
                                        gridNodes.get(mouseNode.getNodePosition()).setWall(false);
                                        endNode = mouseNode;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (keyCode == BACKSPACE) {
            if (startNode != null && endNode != null) {
                if (currentNode == null) {
                    currentNode = startNode;
                }
                aStar(currentNode);
            }
        }

        if (key == 'r') {
            activeSet.clear();
            currentNode = null;

            for (Node node: gridNodes.values()) {
                node.setWall(false);
                node.setActive(false);
                node.setChecked(false);
                node.setStart(false);
                node.setEnd(false);
                node.setInPath(false);
            }
        }

        if (key == 'g') {
            double randNum;

            for (Node node: gridNodes.values()) {
                randNum = Math.random();

                if (randNum < 0.5) {
                    node.setWall(true);
                } else {
                    node.setWall(false);
                }
            }

            key = 'n';
        }
    }

    public void aStar(Node currNode){
        if (!currNode.equals(endNode)) {
            currentNode.setCurrNode(false);
            int minFCost, minHCost;
            Node nextNode;

            if (activeSet.isEmpty()) {
                activeSet.put(currNode.getNodePosition(), currNode);
            }

            addToActive(currNode);

            for (Node nodeToCheck : activeSet.values()) {
                nodeToCheck.setgCost(Math.abs(startNode.getNodePosition().getxPos() - nodeToCheck.getNodePosition().getxPos()) + Math.abs(startNode.getNodePosition().getyPos() - nodeToCheck.getNodePosition().getyPos()));
                nodeToCheck.sethCost(Math.abs(endNode.getNodePosition().getxPos() - nodeToCheck.getNodePosition().getxPos()) + Math.abs(endNode.getNodePosition().getyPos() - nodeToCheck.getNodePosition().getyPos()));
                nodeToCheck.setfCost(nodeToCheck.getgCost() + nodeToCheck.gethCost());
            }

            minFCost = activeSet.get(activeSet.keySet().toArray()[0]).getfCost();
            minHCost = activeSet.get(activeSet.keySet().toArray()[0]).gethCost();
            nextNode = activeSet.get(activeSet.keySet().toArray()[0]);

            currNode.setChecked(true);
            activeSet.remove(currNode.getNodePosition());

            for (Node node : activeSet.values()) {
                if (node.getfCost() < minFCost) {
                    minFCost = node.getfCost();
                    nextNode = node;
                    minHCost = node.gethCost();
                }
            }

            for (Node node : activeSet.values()) {
                if (node.getfCost() == minFCost) {
                    if (node.gethCost() < minHCost) {
                        minHCost = node.gethCost();
                        nextNode = node;
                    }
                }
            }

            currentNode = nextNode;
            currentNode.setCurrNode(true);
        } else {
            fillPath(endNode);
        }
    }

    public void addToActive(Node currNode){
        for (Vector2D neighborPos : gridNodes.keySet()){
            if (currNode.getNodePosition().getxPos() + currNode.getNodeSize().getxPos() == neighborPos.getxPos()) {
                if (currNode.getNodePosition().getyPos() + currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                } else if (currNode.getNodePosition().getyPos() - currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                } else if (currNode.getNodePosition().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                }
            } else if (currNode.getNodePosition().getxPos() - currNode.getNodeSize().getxPos() == neighborPos.getxPos()) {
                if (currNode.getNodePosition().getyPos() + currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                } else if (currNode.getNodePosition().getyPos() - currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                } else if (currNode.getNodePosition().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                }
            } else if (currNode.getNodePosition().getxPos() == neighborPos.getxPos()) {
                if (currNode.getNodePosition().getyPos() + currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                } else if (currNode.getNodePosition().getyPos() - currNode.getNodeSize().getyPos() == neighborPos.getyPos()) {
                    if (!gridNodes.get(neighborPos).isWall() && !gridNodes.get(neighborPos).isChecked()) {
                        activeSet.put(neighborPos, gridNodes.get(neighborPos));
                        gridNodes.get(neighborPos).setParent(currNode);
                    }
                }
            }
        }

        for (Node node : gridNodes.values()) {
            if (activeSet.containsValue(node)) {
                node.setActive(true);
            } else {
                node.setActive(false);
            }
        }
    }

    public void fillPath(Node currNode) {
        if (currNode.isStart()) {
            currNode.setInPath(true);
        } else {
            currNode.setInPath(true);
            fillPath(currNode.getParent());
        }
    }
}
