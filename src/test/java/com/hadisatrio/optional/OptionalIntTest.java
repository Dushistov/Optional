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

import com.hadisatrio.optional.function.Function;
import com.hadisatrio.optional.function.IntConsumer;
import com.hadisatrio.optional.function.IntSupplier;
import com.hadisatrio.optional.function.Supplier;
import org.junit.Assert;
import org.junit.Test;

public class OptionalIntTest {

    @Test
    public void of() throws Exception {
        Assert.assertTrue(OptionalInt.of(42).isPresent());
    }

    @Test
    public void empty() throws Exception {
        Assert.assertFalse(OptionalInt.empty().isPresent());
    }

    @Test
    public void isPresent() throws Exception {
        Assert.assertTrue(OptionalInt.of(42).isPresent());

        Assert.assertFalse(OptionalInt.empty().isPresent());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(42, OptionalInt.of(42).get());

        try {
            OptionalInt.empty().get();

            Assert.fail("Invoking get() on an empty optional should raise an exception.");
        } catch (IllegalStateException expected) {
            // No-op. This is the expected behaviour.
        }
    }

    @Test
    public void orElse() throws Exception {
        Assert.assertEquals(42, OptionalInt.empty().orElse(42));
    }

    @Test
    public void orWithSupplier() throws Exception {
        Assert.assertEquals(42, OptionalInt.empty().orElse(new IntSupplier() {
            @Override
            public int get() {
                return 42;
            }
        }));
    }

    @Test
    public void orThrow() throws Exception {
        try {
            OptionalInt.empty().orThrow(new Exception("An exception occurred."));

            Assert.fail("Invoking orThrow() on an empty optional should throw an exception.");
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
            OptionalInt.empty().orThrow(anExceptionSupplier);

            Assert.fail("Invoking orThrow() on an empty optional should throw an exception.");
        } catch (Exception anException) {
            // No-op. This is the expected behaviour.
        }
    }

    @Test
    public void ifPresent() throws Exception {
        OptionalInt.of(42).ifPresent(new IntConsumer() {
            @Override
            public void consume(int value) {
                Assert.assertEquals(42, value);
            }
        });

        OptionalInt.empty().ifPresent(new IntConsumer() {
            @Override
            public void consume(int value) {
                Assert.fail("ifPresent() on an empty optional should never call its consumer");
            }
        });
    }

    @Test
    public void ifPresentOrElse() throws Exception {
        OptionalInt.of(42).ifPresentOrElse(new IntConsumer() {
            @Override
            public void consume(int value) {
                Assert.assertEquals(42, value);
            }
        }, new Function() {
            @Override
            public void call() {
                Assert.fail("ifPresent() on an non-empty optional should call its consumer");
            }
        });

        OptionalInt.empty().ifPresentOrElse(new IntConsumer() {
            @Override
            public void consume(int value) {
                Assert.fail("ifPresent() on an empty optional should never call its consumer");
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
