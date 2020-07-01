package list;

import pack.Car;

import java.util.*;
import java.util.function.Consumer;

public class CarParkList implements List<Car> {
    private static final Car[] EMPTY_ELEMENTDATA = new Car[0];
    private static final Car[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Car[15];
    private Car[] elementData;
    private int size;

    public CarParkList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Car[initialCapacity];
        } else {
            if (initialCapacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }

            this.elementData = EMPTY_ELEMENTDATA;
        }

    }

    public CarParkList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public CarParkList(Collection<? extends Car> c) {
        this.elementData = new Car[c.size()];
        int i = 0;
        for (Object o : c.toArray()) {
            this.elementData[i] = (Car) o;
            i++;
        }


        if ((this.size = this.elementData.length) != 0) {
            if (this.elementData.getClass() != Car[].class) {
                this.elementData = Arrays.copyOf(this.elementData, this.size, Car[].class);
            }
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size) {
            return (T[]) Arrays.copyOf(this.elementData, this.size, a.getClass());
        } else {
            System.arraycopy(this.elementData, 0, a, 0, this.size);
            if (a.length > this.size) {
                a[this.size] = null;
            }

            return a;
        }
    }

    @Override
    public boolean add(Car car) {
        this.add(car, this.elementData, this.size);
        return true;
    }

    private void add(Car car, Car[] elementData, int s) {
        if (s == elementData.length) {
            elementData = this.grow();
        }

        elementData[s] = car;
        this.size = s + 1;
    }

    @Override
    public boolean remove(Object o) {
        Object[] es = this.elementData;
        int size = this.size;
        int i = 0;
        if (o == null) {
            while (true) {
                if (i >= size) {
                    return false;
                }

                if (es[i] == null) {
                    break;
                }

                ++i;
            }
        } else {
            while (true) {
                if (i >= size) {
                    return false;
                }

                if (o.equals(es[i])) {
                    break;
                }

                ++i;
            }
        }

        this.fastRemove(es, i);
        return true;
    }

    @Override
    public Car remove(int index) {
        Objects.checkIndex(index, this.size);
        Car[] es = this.elementData;
        Car oldValue = es[index];
        this.fastRemove(es, index);
        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        int newSize;
        if ((newSize = this.size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }

        es[this.size = newSize] = null;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return this.batchRemove(collection, false, 0, this.size);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return this.batchRemove(collection, true, 0, this.size);
    }

    boolean batchRemove(Collection<?> c, boolean complement, int from, int end) {
        Objects.requireNonNull(c);
        Car[] es = this.elementData;

        for (int r = from; r != end; ++r) {
            if (c.contains(es[r]) != complement) {
                int w = r++;

                try {
                    for (; r < end; ++r) {
                        Car e;
                        if (c.contains(e = es[r]) == complement) {
                            es[w++] = e;
                        }
                    }
                } catch (Throwable var12) {
                    System.arraycopy(es, r, es, w, end - r);
                    w += end - r;
                    throw var12;
                } finally {
                    this.shiftTailOverGap(es, w, end);
                }

                return true;
            }
        }

        return false;
    }

    private void shiftTailOverGap(Object[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, this.size - hi);
        int to = this.size;

        for (int i = this.size -= hi - lo; i < to; ++i) {
            es[i] = null;
        }

    }

    @Override
    public void clear() {
        Object[] es = this.elementData;
        int to = this.size;

        for (int i = this.size = 0; i < to; ++i) {
            es[i] = null;
        }
    }

    @Override
    public Car get(int index) {
        Objects.checkIndex(index, this.size);
        return this.elementData[index];
    }

    @Override
    public Car set(int index, Car car) {
        Objects.checkIndex(index, this.size);
        Car oldValue = this.elementData[index];
        this.elementData[index] = car;
        return oldValue;
    }

    public void add(int index, Car car) {
        this.rangeCheckForAdd(index);
        int s;
        Object[] elementData;
        if ((s = this.size) == (elementData = this.elementData).length) {
            elementData = this.grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = car;
        this.size = s + 1;
    }

    private Car[] grow() {
        return this.grow(this.size + (int) Math.ceil(this.size * 0.3));
    }

    private Car[] grow(int minCapacity) {
        return this.elementData = Arrays.copyOf(this.elementData, minCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }


    @Override
    public int indexOf(Object o) {
        return this.indexOfRange(o, 0, this.size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = this.elementData;
        int i;
        if (o == null) {
            for (i = start; i < end; ++i) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (i = start; i < end; ++i) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.lastIndexOfRange(o, 0, this.size);
    }

    int lastIndexOfRange(Object o, int start, int end) {
        Object[] es = this.elementData;
        int i;
        if (o == null) {
            for (i = end - 1; i >= start; --i) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (i = end - 1; i >= start; --i) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o: collection){
            if(!this.contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Car> collection) {
        Object[] a = collection.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        } else {
            Object[] elementData;
            int s;
            if (numNew > (elementData = this.elementData).length - (s = this.size)) {
                elementData = this.grow(s + numNew);
            }

            System.arraycopy(a, 0, elementData, s, numNew);
            this.size = s + numNew;
            return true;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends Car> collection) {
        this.rangeCheckForAdd(index);
        Object[] a = collection.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        } else {
            Object[] elementData;
            int s;
            if (numNew > (elementData = this.elementData).length - (s = this.size)) {
                elementData = this.grow(s + numNew);
            }

            int numMoved = s - index;
            if (numMoved > 0) {
                System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
            }

            System.arraycopy(a, 0, elementData, index, numNew);
            this.size = s + numNew;
            return true;
        }

    }

    @Override
    public Iterator<Car> iterator(){
        return new CarParkList.Itr();
    }

    @Override
    public ListIterator<Car> listIterator() {
        return new CarParkList.ListItr(0);
    }

    @Override
    public ListIterator<Car> listIterator(int i) {
        this.rangeCheckForAdd(i);
        return new CarParkList.ListItr(i);
    }

    static <Car> Car elementAt(Car[] es, int index) {
        return es[index];
    }

    @Override
    public List<Car> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, this.size);
        return new CarParkList.SubList(this, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        } else if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        } else if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    protected void removeRange(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        } else {
            this.shiftTailOverGap(this.elementData, fromIndex, toIndex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarParkList cars = (CarParkList) o;
        return size == cars.size &&
                Arrays.equals(elementData, cars.elementData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elementData);
        return result;
    }

    private static class SubList extends AbstractList<Car>{
        private final CarParkList root;
        private final CarParkList.SubList parent;
        private final int offset;
        private int size;

        public SubList(CarParkList root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        private SubList(CarParkList.SubList parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
        }

        public Car set(int index, Car element) {
            Objects.checkIndex(index, this.size);
            Car oldValue = this.root.elementData[this.offset + index];
            this.root.elementData[this.offset + index] = element;
            return oldValue;
        }

        public Car get(int index) {
            Objects.checkIndex(index, this.size);
            return this.root.elementData[this.offset + index];
        }

        public int size() {
            return this.size;
        }

        public void add(int index, Car element) {
            this.rangeCheckForAdd(index);
            this.root.add(this.offset + index, element);
        }

        public Car remove(int index) {
            Objects.checkIndex(index, this.size);
            Car result = this.root.remove(this.offset + index);
            return result;
        }

        protected void removeRange(int fromIndex, int toIndex) {
            this.root.removeRange(this.offset + fromIndex, this.offset + toIndex);
            this.updateSize(fromIndex - toIndex);
        }

        public boolean addAll(Collection<? extends Car> c) {
            return this.addAll(this.size, c);
        }

        public boolean addAll(int index, Collection<? extends Car> c) {
            this.rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize == 0) {
                return false;
            } else {
                this.root.addAll(this.offset + index, c);
                this.updateSize(cSize);
                return true;
            }
        }

        public boolean removeAll(Collection<?> c) {
            return this.batchRemove(c, false);
        }

        public boolean retainAll(Collection<?> c) {
            return this.batchRemove(c, true);
        }

        private boolean batchRemove(Collection<?> c, boolean complement) {
            int oldSize = this.root.size;
            boolean modified = this.root.batchRemove(c, complement, this.offset, this.offset + this.size);
            if (modified) {
                this.updateSize(this.root.size - oldSize);
            }

            return modified;
        }

        public Object[] toArray() {
            return Arrays.copyOfRange(this.root.elementData, this.offset, this.offset + this.size);
        }

        public <T> T[] toArray(T[] a) {
            if (a.length < this.size) {
                return (T[]) Arrays.copyOfRange(this.root.elementData, this.offset, this.offset + this.size, a.getClass());
            } else {
                System.arraycopy(this.root.elementData, this.offset, a, 0, this.size);
                if (a.length > this.size) {
                    a[this.size] = null;
                }

                return a;
            }
        }

        public int indexOf(Object o) {
            int index = this.root.indexOfRange(o, this.offset, this.offset + this.size);
            return index >= 0 ? index - this.offset : -1;
        }

        public int lastIndexOf(Object o) {
            int index = this.root.lastIndexOfRange(o, this.offset, this.offset + this.size);
            return index >= 0 ? index - this.offset : -1;
        }

        public boolean contains(Object o) {
            return this.indexOf(o) >= 0;
        }

        public Iterator<Car> iterator() {
            return this.listIterator();
        }

        public ListIterator<Car> listIterator(final int index) {
            this.rangeCheckForAdd(index);
            return new ListIterator<Car>() {
                int cursor = index;
                int lastRet = -1;

                public boolean hasNext() {
                    return this.cursor != CarParkList.SubList.this.size;
                }

                public Car next() {
                    int i = this.cursor;
                    if (i >= CarParkList.SubList.this.size) {
                        throw new NoSuchElementException();
                    } else {
                        Car[] elementData = CarParkList.SubList.this.root.elementData;
                        if (CarParkList.SubList.this.offset + i >= elementData.length) {
                            throw new ConcurrentModificationException();
                        } else {
                            this.cursor = i + 1;
                            return elementData[CarParkList.SubList.this.offset + (this.lastRet = i)];
                        }
                    }
                }

                public boolean hasPrevious() {
                    return this.cursor != 0;
                }

                public Car previous() {
                    int i = this.cursor - 1;
                    if (i < 0) {
                        throw new NoSuchElementException();
                    } else {
                        Car[] elementData = CarParkList.SubList.this.root.elementData;
                        if (CarParkList.SubList.this.offset + i >= elementData.length) {
                            throw new ConcurrentModificationException();
                        } else {
                            this.cursor = i;
                            return elementData[CarParkList.SubList.this.offset + (this.lastRet = i)];
                        }
                    }
                }

                public void forEachRemaining(Consumer<? super Car> action) {
                    Objects.requireNonNull(action);
                    int size = CarParkList.SubList.this.size;
                    int i = this.cursor;
                    if (i < size) {
                        Car[] es = CarParkList.SubList.this.root.elementData;
                        if (CarParkList.SubList.this.offset + i >= es.length) {
                            throw new ConcurrentModificationException();
                        }

                        while(i < size) {
                            action.accept(CarParkList.elementAt(es, CarParkList.SubList.this.offset + i));
                            ++i;
                        }

                        this.cursor = i;
                        this.lastRet = i - 1;
                    }

                }

                public int nextIndex() {
                    return this.cursor;
                }

                public int previousIndex() {
                    return this.cursor - 1;
                }

                public void remove() {
                    if (this.lastRet < 0) {
                        throw new IllegalStateException();
                    } else {

                        try {
                            CarParkList.SubList.this.remove(this.lastRet);
                            this.cursor = this.lastRet;
                            this.lastRet = -1;
                        } catch (IndexOutOfBoundsException var2) {
                            throw new ConcurrentModificationException();
                        }
                    }
                }

                public void set(Car e) {
                    if (this.lastRet < 0) {
                        throw new IllegalStateException();
                    } else {

                        try {
                            CarParkList.SubList.this.root.set(CarParkList.SubList.this.offset + this.lastRet, e);
                        } catch (IndexOutOfBoundsException var3) {
                            throw new ConcurrentModificationException();
                        }
                    }
                }

                public void add(Car e) {

                    try {
                        int i = this.cursor;
                        CarParkList.SubList.this.add(i, e);
                        this.cursor = i + 1;
                        this.lastRet = -1;
                    } catch (IndexOutOfBoundsException var3) {
                        throw new ConcurrentModificationException();
                    }
                }

            };
        }

        public List<Car> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, this.size);
            return new CarParkList.SubList(this, fromIndex, toIndex);
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
            }
        }

        private String outOfBoundsMsg(int index) {
            return "Index: " + index + ", Size: " + this.size;
        }

        private void updateSize(int sizeChange) {
            CarParkList.SubList slist = this;

            do {
                slist.size += sizeChange;
                slist = slist.parent;
            } while(slist != null);

        }


    }

    private class ListItr extends CarParkList.Itr implements ListIterator<Car> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public Car previous() {
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                Car[] elementData = CarParkList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i;
                    return elementData[this.lastRet = i];
                }
            }
        }

        public void set(Car e) {
            CarParkList.this.set(this.lastRet, e);
        }

        public void add(Car e) {
            int i = this.cursor;
            CarParkList.this.add(i, e);
            this.cursor = i + 1;
            this.lastRet = -1;
        }
    }

    private class Itr implements Iterator<Car> {
        int cursor;
        int lastRet = -1;

        Itr() {
        }

        public boolean hasNext() {
            return this.cursor != CarParkList.this.size;
        }

        public Car next() {
            int i = this.cursor;
            if (i >= CarParkList.this.size) {
                throw new NoSuchElementException();
            } else {
                Car[] elementData = CarParkList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i + 1;
                    return elementData[this.lastRet = i];
                }
            }
        }

        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {

                try {
                    CarParkList.this.remove(this.lastRet);
                    this.cursor = this.lastRet;
                    this.lastRet = -1;
                } catch (IndexOutOfBoundsException var2) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        public void forEachRemaining(Consumer<? super Car> action) {
            Objects.requireNonNull(action);
            int size = CarParkList.this.size;
            int i = this.cursor;
            if (i < size) {
                Car[] es = CarParkList.this.elementData;
                if (i >= es.length) {
                    throw new ConcurrentModificationException();
                }

                while(i < size) {
                    action.accept(CarParkList.elementAt(es, i));
                    ++i;
                }

                this.cursor = i;
                this.lastRet = i - 1;
            }

        }


    }
}
