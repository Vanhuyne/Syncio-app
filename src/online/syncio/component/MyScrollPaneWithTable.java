package online.syncio.component;

public class MyScrollPaneWithTable extends MyScrollPane {
    private MyTable table;
    
    public MyScrollPaneWithTable() {
        table = new MyTable();
        setViewportView(table);
    }

    public MyTable getTable() {
        return table;
    }

    public void setTable(MyTable table) {
        this.table = table;
    }
    
}
