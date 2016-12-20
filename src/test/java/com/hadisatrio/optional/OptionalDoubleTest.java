/*
 *     Copyright (C) 2016 Hadi Satrio
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package com.hadisatrio.optional;

import com.hadisatrio.optional.function.DoubleConsumer;
import com.hadisatrio.optional.function.DoubleSupplier;
import com.hadisatrio.optional.function.Function;
import com.hadisatrio.optional.function.Supplier;
import org.junit.Assert;
import org.junit.Test;

public class OptionalDoubleTest {

    @Test
    public void of() throws Exception {
        Assert.assertTrue(OptionalDouble.of(42d).isPresent());
    }

    @Test
    public void absent() throws Exception {
        Assert.assertFalse(OptionalDouble.absent().isPresent());
    }

    @Test
    public void isPresent() throws Exception {
        Assert.assertTrue(OptionalDouble.of(42d).isPresent());

        Assert.assertFalse(OptionalDouble.absent().isPresent());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(42d, OptionalDouble.of(42d).get(), 0);

        try {
            OptionalDouble.absent().get();

            Assert.fail("Invoking get() on an absent optional should raise an exception.");
        } catch (IllegalStateException expected) {
            // No-op. This is the expected behaviour.
        }
    }

    @Test
    public void or() throws Exception {
        Assert.assertEquals(42d, OptionalDouble.absent().or(42d), 0);
    }

    @Test
    public void orWithSupplier() throws Exception {
        Assert.assertEquals(42d, OptionalDouble.absent().or(new DoubleSupplier() {
            @Override
            public double get() {
                return 42d;
            }
        }), 0);
    }

    @Test
    public void orThrow() throws Exception {
        try {
            OptionalDouble.absent().orThrow(new Exception("An exception occurred."));

            Assert.fail("Invoking orThrow() on an absent optional should throw an exception.");
        } catch (Exception anException) {
            // No-op. This is the expected behaviour.
        }
    }

    @Test
    public void orThrowWithSupplier() throws Exception {
        final Supplier<Exception> anExceptionSupplier = new Supplier<Exception>() {
            @Override
            public Exception get() {
                return new Exception("An exception occurred.");
            }
        };

        try {
            OptionalDouble.absent().orThrow(anExceptionSupplier);

            Assert.fail("Invoking orThrow() on an absent optional should throw an exception.");
        } catch (Exception anException) {
            // No-op. This is the expected behaviour.
        }
    }

    @Test
    public void ifPresent() throws Exception {
        OptionalDouble.of(42d).ifPresent(new DoubleConsumer() {
            @Override
            public void consume(double value) {
                Assert.assertEquals(42d, value, 0);
            }
        });

        OptionalDouble.absent().ifPresent(new DoubleConsumer() {
            @Override
            public void consume(double value) {
                Assert.fail("ifPresent() on an absent optional should never call its consumer");
            }
        });
    }

    @Test
    public void ifPresentOrElse() throws Exception {
        OptionalDouble.of(42d).ifPresentOrElse(new DoubleConsumer() {
            @Override
            public void consume(double value) {
                Assert.assertEquals(42d, value, 0);
            }
        }, new Function() {
            @Override
            public void call() {
                Assert.fail("ifPresent() on an non-absent optional should call its consumer");
            }
        });

        OptionalDouble.absent().ifPresentOrElse(new DoubleConsumer() {
            @Override
            public void consume(double value) {
                Assert.fail("ifPresent() on an absent optional should never call its consumer");
            }
        }, new Function() {
            @Override
            public void call() {
                Assert.assertTrue(true);
                // No-op. This is the expected behaviour.
            }
        });
    }
}
