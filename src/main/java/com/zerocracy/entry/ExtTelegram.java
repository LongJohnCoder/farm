/**
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.entry;

import com.zerocracy.jstk.Farm;
import com.zerocracy.radars.telegram.TmSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.cactoos.Scalar;
import org.cactoos.func.StickyFunc;
import org.cactoos.func.SyncFunc;
import org.cactoos.func.UncheckedFunc;

/**
 * Telegram sessions.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.11
 */
public final class ExtTelegram implements Scalar<Map<Long, TmSession>> {

    /**
     * The singleton.
     */
    private static final UncheckedFunc<Farm, Map<Long, TmSession>> SINGLETON =
        new UncheckedFunc<>(
            new SyncFunc<>(
                new StickyFunc<Farm, Map<Long, TmSession>>(
                    frm -> new ConcurrentHashMap<>(0)
                )
            )
        );

    /**
     * The farm.
     */
    private final Farm farm;

    /**
     * Ctor.
     * @param frm The farm
     */
    public ExtTelegram(final Farm frm) {
        this.farm = frm;
    }

    @Override
    public Map<Long, TmSession> value() {
        return ExtTelegram.SINGLETON.apply(this.farm);
    }

}