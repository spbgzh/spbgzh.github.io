package com.epam.rd.autotasks.spliterators;

import java.util.function.IntConsumer;

public class rectangularSpliteratorClass implements RectangularSpliterator{
        final int rows; // 行数
        final int columns; // 列数
        final boolean judge;
        final int fence;
        private final int[][] array;
        int origin;

        public rectangularSpliteratorClass(int[][] array)
        {
            this(array,0,array.length);
        }

        public rectangularSpliteratorClass(int[][] array,int origin,int fence)
        {
            this.array=array;
            this.rows=array.length;
            this.columns=array[0].length;
            this.fence=rows*columns;
            this.origin=0;
            judge = rows > columns ? false : true;
        }
        @Override
        public RectangularSpliterator trySplit() {
            int lo = origin, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                    ? null
                    : new rectangularSpliteratorClass(array);
        }

        @Override
        public boolean tryAdvance(IntConsumer action)
        {

            return false;
        }


    @Override
        public long estimateSize()
        {
            return 1;
        }

    @Override
    public int characteristics() {
        return 0;
    }
}
