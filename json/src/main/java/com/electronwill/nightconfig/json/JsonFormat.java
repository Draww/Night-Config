package com.electronwill.nightconfig.json;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;

/**
 * @author TheElectronWill
 */
public abstract class JsonFormat<W extends ConfigWriter<UnmodifiableConfig>>
		implements ConfigFormat<Config, Config, UnmodifiableConfig> {

	private static final JsonFormat<FancyJsonWriter> FANCY = new JsonFormat<FancyJsonWriter>() {
		@Override
		public FancyJsonWriter createWriter() {
			return new FancyJsonWriter();
		}

		@Override
		public ConfigParser<Config, Config> createParser() {
			return new JsonParser(this);
		}
	};
	private static final JsonFormat<MinimalJsonWriter> MINIMAL = new JsonFormat<MinimalJsonWriter>() {
		@Override
		public MinimalJsonWriter createWriter() {
			return new MinimalJsonWriter();
		}

		@Override
		public ConfigParser<Config, Config> createParser() {
			return new JsonParser(this);
		}
	};

	public static JsonFormat<FancyJsonWriter> fancyInstance() {
		return FANCY;
	}

	public static JsonFormat<MinimalJsonWriter> minimalInstance() {
		return MINIMAL;
	}

	private JsonFormat() {}

	@Override
	public abstract W createWriter();

	@Override
	public abstract ConfigParser<Config, Config> createParser();

	@Override
	public Config createConfig() {
		return Config.of(this);
	}

	@Override
	public boolean supportsComments() {
		return false;
	}
}