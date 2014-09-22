/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 CubeEngineDev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.cubeisland.engine.i18n;

import de.cubeisland.engine.i18n.language.SourceLanguage;
import de.cubeisland.engine.i18n.loader.GettextLoader;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class GettextTest
{
    private static final String RESOURCE_PATH = "./src/test/resources/gettext/";

    @Test
    public void testPoGettextTranslation() throws Exception
    {
        List<URL> poFiles = new ArrayList<URL>(1);
        poFiles.add(new File(RESOURCE_PATH, "de_DE.po").toURL());

        GettextLoader gettextLoader = new GettextLoader(Charset.forName("UTF-8"), poFiles);
        I18nService service = new I18nService(SourceLanguage.EN_US, gettextLoader, new I18nLanguageLoader(), Locale.US);

        assertEquals("ja", service.translate(Locale.GERMAN, "yes"));
        assertEquals("nein", service.translate(Locale.GERMAN, "no"));
        assertEquals("Wir haben %d Affen", service.translateN(Locale.GERMAN, "One monkey is better than sheep", "We've %d monkeys", 0));
        assertEquals("Ein Affe ist besser als ein Schaf", service.translateN(Locale.GERMAN, "One monkey is better than sheep", "We've %d monkeys", 1));
        assertEquals("Wir haben %d Affen", service.translateN(Locale.GERMAN, "One monkey is better than sheep", "We've %d monkeys", 2));
        assertEquals("Wir haben %d Affen", service.translateN(Locale.GERMAN, "One monkey is better than sheep", "We've %d monkeys", 23));

        assertEquals("yes", service.translate(Locale.US, "yes"));
        assertEquals("yes", service.translate(Locale.FRENCH, "yes"));
        assertEquals("no", service.translate(Locale.US, "no"));
        assertEquals("no", service.translate(Locale.FRENCH, "no"));
        assertEquals("We've %d monkeys", service.translateN(Locale.US, "One monkey is better than sheep", "We've %d monkeys", 0));
        assertEquals("One monkey is better than sheep", service.translateN(Locale.US, "One monkey is better than sheep", "We've %d monkeys", 1));
        assertEquals("We've %d monkeys", service.translateN(Locale.US, "One monkey is better than sheep", "We've %d monkeys", 2));
    }
}
