
public class Ex04 {
    @Override
    public Optional <OUT> reduce(BinaryOperator <OUT> accumulator) {
        boolean foundAny = false;
        OUT res = null;
        while(iterator.hasNext()){
            if(!foundAny){
                foundAny = true;
                res = (OUT) iterator.next();
            }
            res = accumulator.apply(res, (OUT) iterator.next());
        }
        return foundAny ? Optional.of(res) : Optional.empty();
    }
}