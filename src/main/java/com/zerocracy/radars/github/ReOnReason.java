/**
 * Copyright (c) 2016 Zerocracy
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
package com.zerocracy.radars.github;

import com.zerocracy.jstk.Farm;
import java.io.IOException;
import javax.json.JsonObject;

/**
 * React on specific reason.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ReOnReason implements Reaction {

    /**
     * Reason.
     */
    private final String reason;

    /**
     * Reaction.
     */
    private final Reaction origin;

    /**
     * Ctor.
     * @param rson Reason
     * @param orgn Original reaction
     */
    public ReOnReason(final String rson, final Reaction orgn) {
        this.reason = rson;
        this.origin = orgn;
    }

    @Override
    public void react(final Farm farm, final JsonObject event)
        throws IOException {
        if (event.getString("reason").equals(this.reason)) {
            this.origin.react(farm, event);
        }
    }
}