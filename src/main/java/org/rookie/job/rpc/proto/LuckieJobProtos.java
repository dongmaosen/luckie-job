// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LuckieJob.proto

package org.rookie.job.rpc.proto;

public final class LuckieJobProtos {
  private LuckieJobProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LuckieJobOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 event_id = 1;
    /**
     * <code>required int32 event_id = 1;</code>
     */
    boolean hasEventId();
    /**
     * <code>required int32 event_id = 1;</code>
     */
    int getEventId();

    // required string args = 2;
    /**
     * <code>required string args = 2;</code>
     */
    boolean hasArgs();
    /**
     * <code>required string args = 2;</code>
     */
    java.lang.String getArgs();
    /**
     * <code>required string args = 2;</code>
     */
    com.google.protobuf.ByteString
        getArgsBytes();
  }
  /**
   * Protobuf type {@code LuckieJob}
   */
  public static final class LuckieJob extends
      com.google.protobuf.GeneratedMessage
      implements LuckieJobOrBuilder {
    // Use LuckieJob.newBuilder() to construct.
    private LuckieJob(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LuckieJob(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LuckieJob defaultInstance;
    public static LuckieJob getDefaultInstance() {
      return defaultInstance;
    }

    public LuckieJob getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LuckieJob(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              eventId_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              args_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.rookie.job.rpc.proto.LuckieJobProtos.internal_static_LuckieJob_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.rookie.job.rpc.proto.LuckieJobProtos.internal_static_LuckieJob_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.class, org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.Builder.class);
    }

    public static com.google.protobuf.Parser<LuckieJob> PARSER =
        new com.google.protobuf.AbstractParser<LuckieJob>() {
      public LuckieJob parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LuckieJob(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LuckieJob> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 event_id = 1;
    public static final int EVENT_ID_FIELD_NUMBER = 1;
    private int eventId_;
    /**
     * <code>required int32 event_id = 1;</code>
     */
    public boolean hasEventId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 event_id = 1;</code>
     */
    public int getEventId() {
      return eventId_;
    }

    // required string args = 2;
    public static final int ARGS_FIELD_NUMBER = 2;
    private java.lang.Object args_;
    /**
     * <code>required string args = 2;</code>
     */
    public boolean hasArgs() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string args = 2;</code>
     */
    public java.lang.String getArgs() {
      java.lang.Object ref = args_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          args_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string args = 2;</code>
     */
    public com.google.protobuf.ByteString
        getArgsBytes() {
      java.lang.Object ref = args_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        args_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      eventId_ = 0;
      args_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasEventId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasArgs()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, eventId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getArgsBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, eventId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getArgsBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code LuckieJob}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJobOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.rookie.job.rpc.proto.LuckieJobProtos.internal_static_LuckieJob_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.rookie.job.rpc.proto.LuckieJobProtos.internal_static_LuckieJob_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.class, org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.Builder.class);
      }

      // Construct using org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        eventId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        args_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.rookie.job.rpc.proto.LuckieJobProtos.internal_static_LuckieJob_descriptor;
      }

      public org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob getDefaultInstanceForType() {
        return org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.getDefaultInstance();
      }

      public org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob build() {
        org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob buildPartial() {
        org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob result = new org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.eventId_ = eventId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.args_ = args_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob) {
          return mergeFrom((org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob other) {
        if (other == org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob.getDefaultInstance()) return this;
        if (other.hasEventId()) {
          setEventId(other.getEventId());
        }
        if (other.hasArgs()) {
          bitField0_ |= 0x00000002;
          args_ = other.args_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasEventId()) {
          
          return false;
        }
        if (!hasArgs()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.rookie.job.rpc.proto.LuckieJobProtos.LuckieJob) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 event_id = 1;
      private int eventId_ ;
      /**
       * <code>required int32 event_id = 1;</code>
       */
      public boolean hasEventId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 event_id = 1;</code>
       */
      public int getEventId() {
        return eventId_;
      }
      /**
       * <code>required int32 event_id = 1;</code>
       */
      public Builder setEventId(int value) {
        bitField0_ |= 0x00000001;
        eventId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 event_id = 1;</code>
       */
      public Builder clearEventId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        eventId_ = 0;
        onChanged();
        return this;
      }

      // required string args = 2;
      private java.lang.Object args_ = "";
      /**
       * <code>required string args = 2;</code>
       */
      public boolean hasArgs() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string args = 2;</code>
       */
      public java.lang.String getArgs() {
        java.lang.Object ref = args_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          args_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string args = 2;</code>
       */
      public com.google.protobuf.ByteString
          getArgsBytes() {
        java.lang.Object ref = args_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          args_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string args = 2;</code>
       */
      public Builder setArgs(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        args_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string args = 2;</code>
       */
      public Builder clearArgs() {
        bitField0_ = (bitField0_ & ~0x00000002);
        args_ = getDefaultInstance().getArgs();
        onChanged();
        return this;
      }
      /**
       * <code>required string args = 2;</code>
       */
      public Builder setArgsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        args_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:LuckieJob)
    }

    static {
      defaultInstance = new LuckieJob(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:LuckieJob)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_LuckieJob_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_LuckieJob_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017LuckieJob.proto\"+\n\tLuckieJob\022\020\n\010event_" +
      "id\030\001 \002(\005\022\014\n\004args\030\002 \002(\tB+\n\030org.rookie.job" +
      ".rpc.protoB\017LuckieJobProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_LuckieJob_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_LuckieJob_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_LuckieJob_descriptor,
              new java.lang.String[] { "EventId", "Args", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
