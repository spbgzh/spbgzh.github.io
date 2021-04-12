package com.epam.rd.autotasks.spliterators;

import java.util.Spliterator;
import java.util.function.IntConsumer;

public interface RectangularSpliterator extends Spliterator.OfInt {

  static RectangularSpliterator of(int[][] array) {
    if(array.equals(null))
      throw new UnsupportedOperationException();
    rectangularSpliteratorClass ans = new rectangularSpliteratorClass(array);
    return ans;
  }

  @Override
  RectangularSpliterator trySplit();

  @Override
  boolean tryAdvance(IntConsumer action);

  @Override
  long estimateSize();
}

