// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Luckie.proto

package org.rookie.job.rpc.proto;

public final class LuckieProto {
  private LuckieProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface LuckieOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Luckie)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.Luckie.Event event = 1;</code>
     * @return The enum numeric value on the wire for event.
     */
    int getEventValue();
    /**
     * <code>.Luckie.Event event = 1;</code>
     * @return The event.
     */
    org.rookie.job.rpc.proto.LuckieProto.Luckie.Event getEvent();

    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */
    int getDataCount();
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */
    boolean containsData(
        java.lang.String key);
    /**
     * Use {@link #getDataMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String>
    getData();
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */
    java.util.Map<java.lang.String, java.lang.String>
    getDataMap();
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    java.lang.String getDataOrDefault(
        java.lang.String key,
        java.lang.String defaultValue);
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    java.lang.String getDataOrThrow(
        java.lang.String key);
  }
  /**
   * <pre>
   *luckie protocol
   * </pre>
   *
   * Protobuf type {@code Luckie}
   */
  public  static final class Luckie extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Luckie)
      LuckieOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Luckie.newBuilder() to construct.
    private Luckie(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Luckie() {
      event_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Luckie();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Luckie(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              int rawValue = input.readEnum();

              event_ = rawValue;
              break;
            }
            case 18: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                data_ = com.google.protobuf.MapField.newMapField(
                    DataDefaultEntryHolder.defaultEntry);
                mutable_bitField0_ |= 0x00000001;
              }
              com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
              data__ = input.readMessage(
                  DataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              data_.getMutableMap().put(
                  data__.getKey(), data__.getValue());
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetData();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.rookie.job.rpc.proto.LuckieProto.Luckie.class, org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder.class);
    }

    /**
     * Protobuf enum {@code Luckie.Event}
     */
    public enum Event
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>HEART_BEAT = 0;</code>
       */
      HEART_BEAT(0),
      /**
       * <code>ELECTION = 1;</code>
       */
      ELECTION(1),
      /**
       * <code>REPLECATION = 2;</code>
       */
      REPLECATION(2),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>HEART_BEAT = 0;</code>
       */
      public static final int HEART_BEAT_VALUE = 0;
      /**
       * <code>ELECTION = 1;</code>
       */
      public static final int ELECTION_VALUE = 1;
      /**
       * <code>REPLECATION = 2;</code>
       */
      public static final int REPLECATION_VALUE = 2;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Event valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static Event forNumber(int value) {
        switch (value) {
          case 0: return HEART_BEAT;
          case 1: return ELECTION;
          case 2: return REPLECATION;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Event>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Event> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Event>() {
              public Event findValueByNumber(int number) {
                return Event.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return org.rookie.job.rpc.proto.LuckieProto.Luckie.getDescriptor().getEnumTypes().get(0);
      }

      private static final Event[] VALUES = values();

      public static Event valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private Event(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:Luckie.Event)
    }

    public static final int EVENT_FIELD_NUMBER = 1;
    private int event_;
    /**
     * <code>.Luckie.Event event = 1;</code>
     * @return The enum numeric value on the wire for event.
     */
    public int getEventValue() {
      return event_;
    }
    /**
     * <code>.Luckie.Event event = 1;</code>
     * @return The event.
     */
    public org.rookie.job.rpc.proto.LuckieProto.Luckie.Event getEvent() {
      @SuppressWarnings("deprecation")
      org.rookie.job.rpc.proto.LuckieProto.Luckie.Event result = org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.valueOf(event_);
      return result == null ? org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.UNRECOGNIZED : result;
    }

    public static final int DATA_FIELD_NUMBER = 2;
    private static final class DataDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.String, java.lang.String> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.String, java.lang.String>newDefaultInstance(
                  org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_DataEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> data_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetData() {
      if (data_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            DataDefaultEntryHolder.defaultEntry);
      }
      return data_;
    }

    public int getDataCount() {
      return internalGetData().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    public boolean containsData(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetData().getMap().containsKey(key);
    }
    /**
     * Use {@link #getDataMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getData() {
      return getDataMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
      return internalGetData().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    public java.lang.String getDataOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetData().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; data = 2;</code>
     */

    public java.lang.String getDataOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetData().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (event_ != org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.HEART_BEAT.getNumber()) {
        output.writeEnum(1, event_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeStringMapTo(
          output,
          internalGetData(),
          DataDefaultEntryHolder.defaultEntry,
          2);
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (event_ != org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.HEART_BEAT.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, event_);
      }
      for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
           : internalGetData().getMap().entrySet()) {
        com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
        data__ = DataDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(2, data__);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof org.rookie.job.rpc.proto.LuckieProto.Luckie)) {
        return super.equals(obj);
      }
      org.rookie.job.rpc.proto.LuckieProto.Luckie other = (org.rookie.job.rpc.proto.LuckieProto.Luckie) obj;

      if (event_ != other.event_) return false;
      if (!internalGetData().equals(
          other.internalGetData())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + EVENT_FIELD_NUMBER;
      hash = (53 * hash) + event_;
      if (!internalGetData().getMap().isEmpty()) {
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + internalGetData().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.rookie.job.rpc.proto.LuckieProto.Luckie parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(org.rookie.job.rpc.proto.LuckieProto.Luckie prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     *luckie protocol
     * </pre>
     *
     * Protobuf type {@code Luckie}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Luckie)
        org.rookie.job.rpc.proto.LuckieProto.LuckieOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 2:
            return internalGetData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 2:
            return internalGetMutableData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.rookie.job.rpc.proto.LuckieProto.Luckie.class, org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder.class);
      }

      // Construct using org.rookie.job.rpc.proto.LuckieProto.Luckie.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        event_ = 0;

        internalGetMutableData().clear();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.rookie.job.rpc.proto.LuckieProto.internal_static_Luckie_descriptor;
      }

      @java.lang.Override
      public org.rookie.job.rpc.proto.LuckieProto.Luckie getDefaultInstanceForType() {
        return org.rookie.job.rpc.proto.LuckieProto.Luckie.getDefaultInstance();
      }

      @java.lang.Override
      public org.rookie.job.rpc.proto.LuckieProto.Luckie build() {
        org.rookie.job.rpc.proto.LuckieProto.Luckie result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.rookie.job.rpc.proto.LuckieProto.Luckie buildPartial() {
        org.rookie.job.rpc.proto.LuckieProto.Luckie result = new org.rookie.job.rpc.proto.LuckieProto.Luckie(this);
        int from_bitField0_ = bitField0_;
        result.event_ = event_;
        result.data_ = internalGetData();
        result.data_.makeImmutable();
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.rookie.job.rpc.proto.LuckieProto.Luckie) {
          return mergeFrom((org.rookie.job.rpc.proto.LuckieProto.Luckie)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.rookie.job.rpc.proto.LuckieProto.Luckie other) {
        if (other == org.rookie.job.rpc.proto.LuckieProto.Luckie.getDefaultInstance()) return this;
        if (other.event_ != 0) {
          setEventValue(other.getEventValue());
        }
        internalGetMutableData().mergeFrom(
            other.internalGetData());
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.rookie.job.rpc.proto.LuckieProto.Luckie parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.rookie.job.rpc.proto.LuckieProto.Luckie) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int event_ = 0;
      /**
       * <code>.Luckie.Event event = 1;</code>
       * @return The enum numeric value on the wire for event.
       */
      public int getEventValue() {
        return event_;
      }
      /**
       * <code>.Luckie.Event event = 1;</code>
       * @param value The enum numeric value on the wire for event to set.
       * @return This builder for chaining.
       */
      public Builder setEventValue(int value) {
        event_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.Luckie.Event event = 1;</code>
       * @return The event.
       */
      public org.rookie.job.rpc.proto.LuckieProto.Luckie.Event getEvent() {
        @SuppressWarnings("deprecation")
        org.rookie.job.rpc.proto.LuckieProto.Luckie.Event result = org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.valueOf(event_);
        return result == null ? org.rookie.job.rpc.proto.LuckieProto.Luckie.Event.UNRECOGNIZED : result;
      }
      /**
       * <code>.Luckie.Event event = 1;</code>
       * @param value The event to set.
       * @return This builder for chaining.
       */
      public Builder setEvent(org.rookie.job.rpc.proto.LuckieProto.Luckie.Event value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        event_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.Luckie.Event event = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearEvent() {
        
        event_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.String, java.lang.String> data_;
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetData() {
        if (data_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        return data_;
      }
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetMutableData() {
        onChanged();;
        if (data_ == null) {
          data_ = com.google.protobuf.MapField.newMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        if (!data_.isMutable()) {
          data_ = data_.copy();
        }
        return data_;
      }

      public int getDataCount() {
        return internalGetData().getMap().size();
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public boolean containsData(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        return internalGetData().getMap().containsKey(key);
      }
      /**
       * Use {@link #getDataMap()} instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String> getData() {
        return getDataMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
        return internalGetData().getMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public java.lang.String getDataOrDefault(
          java.lang.String key,
          java.lang.String defaultValue) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetData().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public java.lang.String getDataOrThrow(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetData().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearData() {
        internalGetMutableData().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public Builder removeData(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableData().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String>
      getMutableData() {
        return internalGetMutableData().getMutableMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */
      public Builder putData(
          java.lang.String key,
          java.lang.String value) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        if (value == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableData().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 2;</code>
       */

      public Builder putAllData(
          java.util.Map<java.lang.String, java.lang.String> values) {
        internalGetMutableData().getMutableMap()
            .putAll(values);
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Luckie)
    }

    // @@protoc_insertion_point(class_scope:Luckie)
    private static final org.rookie.job.rpc.proto.LuckieProto.Luckie DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.rookie.job.rpc.proto.LuckieProto.Luckie();
    }

    public static org.rookie.job.rpc.proto.LuckieProto.Luckie getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Luckie>
        PARSER = new com.google.protobuf.AbstractParser<Luckie>() {
      @java.lang.Override
      public Luckie parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Luckie(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Luckie> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Luckie> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.rookie.job.rpc.proto.LuckieProto.Luckie getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Luckie_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Luckie_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Luckie_DataEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Luckie_DataEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Luckie.proto\"\254\001\n\006Luckie\022\034\n\005event\030\001 \001(\016" +
      "2\r.Luckie.Event\022\037\n\004data\030\002 \003(\0132\021.Luckie.D" +
      "ataEntry\032+\n\tDataEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005va" +
      "lue\030\002 \001(\t:\0028\001\"6\n\005Event\022\016\n\nHEART_BEAT\020\000\022\014" +
      "\n\010ELECTION\020\001\022\017\n\013REPLECATION\020\002B\'\n\030org.roo" +
      "kie.job.rpc.protoB\013LuckieProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Luckie_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Luckie_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Luckie_descriptor,
        new java.lang.String[] { "Event", "Data", });
    internal_static_Luckie_DataEntry_descriptor =
      internal_static_Luckie_descriptor.getNestedTypes().get(0);
    internal_static_Luckie_DataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Luckie_DataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
