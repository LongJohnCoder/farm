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
package com.zerocracy.crews.github;

import com.jcabi.github.Comment;
import com.zerocracy.jstk.Farm;
import java.io.IOException;
import java.util.Arrays;

/**
 * React to GitHub comment.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Response {

    /**
     * Respond to the comment.
     * @param farm Farm
     * @param comment Comment in GitHub
     * @throws IOException If fails on I/O
     */
    void react(Farm farm, Comment.Smart comment) throws IOException;

    /**
     * Reactions chained.
     */
    final class Chain implements Response {
        /**
         * Reactions.
         */
        private final Iterable<Response> responses;
        /**
         * Ctor.
         * @param list All responses
         */
        public Chain(final Iterable<Response> list) {
            this.responses = list;
        }
        /**
         * Ctor.
         * @param list All responses
         */
        public Chain(final Response... list) {
            this(Arrays.asList(list));
        }
        @Override
        public void react(final Farm farm, final Comment.Smart comment)
            throws IOException {
            for (final Response response : this.responses) {
                response.react(farm, comment);
            }
        }
    }
}
