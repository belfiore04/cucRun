package Content;

public class Attribute {
    private int value;
    private int maxValue;
    private int minValue;
    private int addBaseValue;
    private int subtractBaseValue;
    public Attribute(int defaultValue){
        this(defaultValue,Integer.MAX_VALUE);
    }
    public Attribute(int defaultValue,int maxValue){
        this.value = defaultValue;
        this.maxValue = maxValue;
        this.minValue = 0;
        this.addBaseValue = 1;
        this.subtractBaseValue = 1;
    }

    /**
     * 属性是否健康 属性值大于最小值表示健康
     * @return
     */
    public boolean isHealthy(){
        return value > minValue;
    }

    public boolean isMax(){
        return value == maxValue;
    }
    /**
     * 按基数增加
     */
    public void add(){
        this.add(this.addBaseValue);
    }

    /**
     * 按指定值增加
     * @param addValue
     */
    public void add(int addValue){
        int i = this.value + addValue;
        this.value = Math.min(i, this.maxValue);
    }
    public void toMax(){
        this.value = this.maxValue;
    }

    /**
     * 按基数减小
     */
    public void subtract(){
        this.subtract(this.subtractBaseValue);
    }

    /**
     * 按指定值减小
     * @param subtractValue
     */
    public void subtract(int subtractValue){
        int i = this.value - subtractValue;
        this.value = Math.max(i, minValue);
    }
    public void addMaxValue(int maxValue) {
        this.maxValue += maxValue;
    }

    public Attribute setValue(int value) {
        this.value = value;
        return this;
    }

    public Attribute setMaxValue(int maxValue) {
        return this;
    }

    public Attribute setMinValue(int minValue) {
        this.minValue = minValue;
        return this;
    }

    public Attribute setAddBaseValue(int addBaseValue) {
        this.addBaseValue = addBaseValue;
        return this;
    }

    public Attribute setSubtractBaseValue(int subtractBaseValue) {
        this.subtractBaseValue = subtractBaseValue;
        return this;
    }

    /**
     * 增加基数与减小基数相同时
     *
     * @param baseValue
     * @return
     */
    public Attribute setSameBaseValue(int baseValue) {
        this.addBaseValue = baseValue;
        this.subtractBaseValue = baseValue;
        return this;
    }

    public int getValue() {
        return value;
    }
}
