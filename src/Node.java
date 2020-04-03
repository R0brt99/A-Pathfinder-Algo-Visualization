

public class Node {

    private Vector2D nodePosition, nodeSize;
    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean isChecked = false;
    private boolean isActive = false;
    private boolean isWall = false;
    private boolean isCurrNode = false;
    private boolean isInPath = false;
    private int gCost, hCost, fCost;
    private Node parent;

    Node(Vector2D pos, Vector2D size){
        nodePosition = pos;
        nodeSize = size;
    }

    public Vector2D getNodePosition() {
        return nodePosition;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Vector2D getNodeSize() {
        return nodeSize;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCurrNode() {
        return isCurrNode;
    }

    public void setCurrNode(boolean currNode) {
        isCurrNode = currNode;
    }

    public boolean isInPath() {
        return isInPath;
    }

    public void setInPath(boolean inPath) {
        isInPath = inPath;
    }
}
