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
package de.cubeisland.engine.i18n.language;

import de.cubeisland.engine.i18n.translation.TranslationContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * This class represents the source language.
 */
public final class SourceLanguage implements Language
{
    private final Locale locale;
    private final String name;
    private final String localName;
    private final TranslationContainer messages;

    protected SourceLanguage(Locale locale, String name, String localName)
    {
        this.locale = locale;
        this.name = name;
        this.localName = localName;
        messages = new TranslationContainer(new HashMap<String, String>(), new HashMap<String, List<String>>());
    }

    public Locale getLocale()
    {
        return this.locale;
    }

    public String getName()
    {
        return this.name;
    }

    public String getLocalName()
    {
        return this.localName;
    }

    public String getTranslation(String singular)
    {
        return this.messages.getSingular(singular);
    }

    public String getTranslation(String plural, int n)
    {
        return this.messages.getPlural(plural, n);
    }

    public TranslationContainer getMessages()
    {
        return this.messages;
    }

    @Override
    public int hashCode()
    {
        return this.locale.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SourceLanguage) || getClass() != obj.getClass())
        {
            return false;
        }
        return this.locale.equals(((SourceLanguage)obj).locale);
    }
}