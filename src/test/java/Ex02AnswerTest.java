import org.pg4200.les02.list.MyList;

class Ex02AnswerTest extends MyListTestTemplate {

    @Override
    protected <T> MyList <T> getNewInstance(Class <T> klass) {
        return new Ex02 <>();
    }
}
