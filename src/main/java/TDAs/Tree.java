package TDAs;

import java.util.Comparator;
import java.util.LinkedList;

public class Tree<T> {
    NodeTree<T> root;

    public Tree(NodeTree<T> root) {
        this.root = root;
    }
    
    public Tree() {
        this.root = null;
    }

    public NodeTree<T> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<T> root) {
        this.root = root;
    }
    
    public LinkedList<Tree<T>> getChildren(){
        return root.getChildren();
    }
    
    public boolean isEmpty(){
        return this==null || this.root==null;
    }
    
    public boolean isLeaf() {
        return this.root.getChildren()==null;
    }
    
    public Tree<T> getChild(int index){
        return this.root.getChildren().get(index);
    }
    
    @Override
    public String toString(){
        String s = this.getRoot().getContent().toString();
        if(this.getChildren()!=null){
            s += "\n----HIJOS----\n";
            for(Tree<T> t: this.getChildren()){
                if(t!=null)
                    s += t.getRoot().getContent().toString();                
            }
            s += "\n-------------\n";
        }
        return s;
    }
    
    public Tree<T> search(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this;
            } else {
                Tree<T> tmp = null;
                if (this.getChildren() != null) {
                    for(Tree<T> t: this.getChildren()){
                        if (tmp == null) {
                            tmp = t.search(content, cmp);
                        }
                    } 
                }
                return tmp;
            }
        }
    }
}
