import org.pg4200.les02.list.MyList;

public class Ex02<T> implements MyList <T> {
    private class ListNode {
        T value;
        ListNode next;
        ListNode previous;
    }

    private ListNode head;
    private ListNode middle;
    private ListNode tail;
    private int size;

    @Override
    public void delete(int index) {
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();

        if(index == 0){
            if(head.next != null){
                head = head.next;
                head.previous = null;
            }else{
                head = null;
                tail = null;
                middle = null;
            }
        }else if(index == (size - 1)){
            tail.previous.next = null;
            tail = tail.previous;
        }else{
            ListNode target = getNode(index);
            target.previous.next = target.next;
            target.next.previous = target.previous;
        }
        size--;
        if(size > 1) middle = getNode(GetMiddleIndex());
    }

    private ListNode getNode(int index) {
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        if(index <= GetMiddleIndex()){
            if(index <= GetMiddleIndex() / 2 + 1){
                ListNode current = head;
                int counter = 0;
                while(current != null){
                    if(counter == index) return current;
                    current = current.next;
                    counter++;
                }
            }else{
                ListNode current = middle;
                int counter = GetMiddleIndex();
                while(current != null){
                    if(counter == index) return current;
                    current = current.previous;
                    counter--;
                }
            }

        }else{
            if(index >= GetMiddleIndex() + (GetMiddleIndex() + 1) / 2){
                ListNode current = middle;
                int counter = GetMiddleIndex();
                while(current != null){
                    if(counter == index) return current;
                    current = current.next;
                    counter++;
                }
            }else{
                ListNode current = tail;
                int counter = 0;
                while(current != null){
                    if(counter == ((size - 1) - index)) return current;
                    current = current.previous;
                    counter++;
                }
            }
        }
        return null;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(int index, T value) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        ListNode node = new ListNode();
        node.value = value;

        if(head == null){
            head = node;
            middle = node;
            tail = node;

        }else if(index == 0){
            head.previous = node;
            node.next = head;
            head = node;

        }else if(index == size){
            tail.next = node;
            node.previous = tail;
            tail = node;

        }else{
            ListNode target = getNode(index);
            ListNode beforeTarget = target.previous;
            beforeTarget.next = node;
            node.previous = beforeTarget;
            node.next = target;
            target.previous = node;
        }
        size++;
        if(size > 1) middle = getNode(GetMiddleIndex());
    }

    @Override
    public int size() {
        return size;
    }

    private int GetMiddleIndex() {
        return (size() - 1) / 2;
    }
}
